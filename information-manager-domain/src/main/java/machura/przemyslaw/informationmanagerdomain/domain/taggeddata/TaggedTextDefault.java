package machura.przemyslaw.informationmanagerdomain.domain.taggeddata;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;

import java.util.List;

@Builder
public class TaggedTextDefault implements TaggedText {
    @Singular
    private final List<Tag> tags;
    @NonNull
    private final String text;

    @Override
    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public String getText() {
        return text;
    }
}
