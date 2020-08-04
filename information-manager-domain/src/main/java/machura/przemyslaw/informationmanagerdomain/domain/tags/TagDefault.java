package machura.przemyslaw.informationmanagerdomain.domain.tags;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;

import java.util.Objects;
import java.util.Set;


public class TagDefault<T> implements Tag<T> {

    @NonNull
    private final String name;
    private final String mainValue;
    private final Set<TagAttribute> attributes;

    @Builder
    public TagDefault(@NonNull String name, String mainValue, @Singular Set<TagAttribute> attributes) {
        this.name = name;
        this.mainValue = Objects.isNull(mainValue) ? "" : mainValue;
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMainValue() {
        return mainValue;
    }

    @Override
    public Set<TagAttribute> getAttributes() {
        return attributes;
    }
}
