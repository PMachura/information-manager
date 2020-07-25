package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.placedin;

import io.vavr.Tuple2;
import io.vavr.control.Either;
import lombok.Builder;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class TagsAttributesExtractorDefault implements TagsAttributesExtractor {

    private final TagsInTextPropertiesSupplier tagsInTextPropertiesSupplier;
    private final TagAttributesBuilder tagAttributesBuilder;
    private final Pattern attributeNameValuePattern;

    @Builder
    public TagsAttributesExtractorDefault(TagsInTextPropertiesSupplier tagsInTextPropertiesSupplier, TagAttributesBuilder tagAttributesBuilder) {
        this.tagsInTextPropertiesSupplier = tagsInTextPropertiesSupplier;
        this.tagAttributesBuilder = tagAttributesBuilder;
        this.attributeNameValuePattern = designateTagAttributeNameValuePattern(this.tagsInTextPropertiesSupplier);
    }

    @Override
    public Either<List<Fault>, List<TagAttribute>> extractAttributes(String tag) {
        return validateTag(tag)
                .map(this::removeStartAndEndMarkers)
                .map(this::extractAttributesFromStrippedTag)
                .map(this::toTagAttributes);
    }

    //todo
    private Either<List<Fault>, String> validateTag(String tag) {
        return Either.right(tag);
    }

    public List<Tuple2<String, String>> extractAttributesFromStrippedTag(String strippedTag) {
        return attributeNameValuePattern.matcher(strippedTag)
                .results()
                .map(MatchResult::group)
                .map(this::extractAttributeNameAndValue)
                .collect(Collectors.toList());
    }

    /*
     todo consider to change set to list to preserve orders and be able to determine main tag attribute (the first one)
     or create value object to hold the first attribute and others
     */
    private List<TagAttribute> toTagAttributes(List<Tuple2<String, String>> attributesNamesWithValues) {
        return attributesNamesWithValues.stream()
                .map(attributeNameValue -> tagAttributesBuilder.build(attributeNameValue._1, attributeNameValue._2))
                .filter(Either::isRight) //todo handle faults (Either.Left) from tagAttributesBuilder.build()
                .map(Either::get)
                .collect(Collectors.toList());
    }

    private Tuple2<String, String> extractAttributeNameAndValue(String attributeNameWithValue) {
        String[] attributeAsFirstValueAsSecond =
                attributeNameWithValue.split(tagsInTextPropertiesSupplier.getAttributesNameValueSeparator(), 2);
        return new Tuple2(attributeAsFirstValueAsSecond[0].strip(),
                removeAttributeValuesEnclosingEndMarkers(attributeAsFirstValueAsSecond[1].strip()));
    }

    private String removeStartAndEndMarkers(String tag) {
        return isTerminatedTag(tag) ?
                removeTagStartAndTerminationMarker(tag) :
                removeTagStartAndEndMarker(tag);

    }

    private boolean isTerminatedTag(String tag) {
        return tag.startsWith(tagsInTextPropertiesSupplier.getStartMarker()) && tag.endsWith(tagsInTextPropertiesSupplier.getTerminationMarker());
    }

    private String removeTagStartAndEndMarker(String tag) {
        return removeFirstAndLastOccurrence(tag, tagsInTextPropertiesSupplier.getStartMarker(), tagsInTextPropertiesSupplier.getEndMarker());
    }

    private String removeTagStartAndTerminationMarker(String tag) {
        return removeFirstAndLastOccurrence(tag, tagsInTextPropertiesSupplier.getStartMarker(), tagsInTextPropertiesSupplier.getTerminationMarker());
    }

    private String removeAttributeValuesEnclosingEndMarkers(String attribute) {
        return removeFirstAndLastOccurrence(attribute,
                tagsInTextPropertiesSupplier.getAttributesValueEnclosingMarker(),
                tagsInTextPropertiesSupplier.getAttributesValueEnclosingMarker());
    }

    private String removeFirstAndLastOccurrence(String toReplace, String first, String last) {
        return toReplace.replaceFirst(first, "")
                .replaceFirst(last + "\\z", "");
    }

    private Pattern designateTagAttributeNameValuePattern(TagsInTextPropertiesSupplier tagsInTextPropertiesSupplier) {
        return Pattern.compile("[^\\s]+"
                + tagsInTextPropertiesSupplier.getAttributesNameValueSeparator()
                + tagsInTextPropertiesSupplier.getAttributesValueEnclosingMarker()
                + "[^" + tagsInTextPropertiesSupplier.getAttributesValueEnclosingMarker() + "]"
                + tagsInTextPropertiesSupplier.getAttributesValueEnclosingMarker()
                + "(\\s|\\Z)");
    }


}
