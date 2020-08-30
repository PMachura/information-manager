package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.deprecated;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileProcessor {

    private final TagsInTextPropertiesSupplier tagsInTextPropertiesSupplier;
    private final Pattern tagPattern;

    public FileProcessor(TagsInTextPropertiesSupplier tagsInTextPropertiesSupplier) {
        this.tagsInTextPropertiesSupplier = tagsInTextPropertiesSupplier;
        this.tagPattern = designateTagPattern(this.tagsInTextPropertiesSupplier);
    }

    public List<String> extractRawTags(String text) {
        return this.tagPattern.matcher(text).results().map(MatchResult::group).collect(Collectors.toList());
    }

    //todo consider to pass allowed tags main attribute (first one) to find valid tags
    private Pattern designateTagPattern(TagsInTextPropertiesSupplier tagsInTextPropertiesSupplier) {
        return Pattern.compile
                (tagsInTextPropertiesSupplier.getStartMarker()
                        + "[^\\s]+.*?"
                        + "(" + tagsInTextPropertiesSupplier.getEndMarker() + "|" + tagsInTextPropertiesSupplier.getTerminationMarker() + ")"
                );
    }
}
