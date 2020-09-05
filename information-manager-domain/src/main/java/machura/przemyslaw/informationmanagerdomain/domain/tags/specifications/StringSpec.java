package machura.przemyslaw.informationmanagerdomain.domain.tags.specifications;

import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttr;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpecImpl;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

//PMa todo create something generic for TagAttrSpec<String>
public class StringSpec implements TagAttrSpec<String> {

    private final TagAttrSpecImpl<String> tagAttrSpec;
    private final Function<String, String> attrValueToObjectMapper = Function.identity();
    private final Function<TagAttr, String> tagAttrToObjectMapper = TagAttr::getValue;
    private final Function<String, String> objToAttrValueMapper = Function.identity();

    public StringSpec(String attrKey, Set<String> valuesRestrictedTo) {
        tagAttrSpec = TagAttrSpecImpl.<String>builder()
                .attributeKey(attrKey)
                .valuesRestrictedTo(valuesRestrictedTo)
                .attrValueToObjectMapper(attrValueToObjectMapper)
                .tagAttrToObjectMapper(tagAttrToObjectMapper)
                .objToAttrValueMapper(objToAttrValueMapper)
                .build();

    }

    public static StringSpec create() {
        return new StringSpec(String.class.getSimpleName(), Set.of());
    }

    @Override
    public String getAttributeKey() {
        return tagAttrSpec.getAttributeKey();
    }

    @Override
    public Set<String> getValuesRestrictedTo() {
        return tagAttrSpec.getValuesRestrictedTo();
    }

    @Override
    public boolean isCompatible(TagAttr tagAttr) {
        return tagAttrSpec.isCompatible(tagAttr);
    }

    @Override
    public boolean isAttrValueValid(String value) {
        return tagAttrSpec.isAttrValueValid(value);
    }

    @Override
    public Optional<String> fromValue(String value) {
        return tagAttrSpec.fromValue(value);
    }

    @Override
    public Optional<String> fromTagAttr(TagAttr tagAttr) {
        return tagAttrSpec.fromTagAttr(tagAttr);
    }

    @Override
    public TagAttr toTagAttr(String object) {
        return tagAttrSpec.toTagAttr(object);
    }

    @Override
    public String toAttrValue(String object) {
        return tagAttrSpec.toAttrValue(object);
    }

    @Override
    public Optional<String> firstMatchFrom(Collection<TagAttr> tagAttrs) {
        return tagAttrSpec.firstMatchFrom(tagAttrs);
    }
}
