package machura.przemyslaw.informationmanagerdomain.domain.tags.maintopictag;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;

import java.util.List;
import java.util.Optional;

@Builder
public class MainTopicTag implements Tag {

    @NonNull
    private final String mainTopicValue;
    @Singular
    private final List<MainTopicTagAttribute> attributes;

    @Override
    public String getName() {
        return "mainTopic";
    }

    @Override
    public Optional<String> getMainValue() {
        return Optional.of(mainTopicValue);
    }

    @Override
    public List<MainTopicTagAttribute> getAttributes() {
        return attributes;
    }
}
