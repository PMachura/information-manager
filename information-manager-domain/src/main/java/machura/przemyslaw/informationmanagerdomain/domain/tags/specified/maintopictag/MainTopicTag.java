package machura.przemyslaw.informationmanagerdomain.domain.tags.specified.maintopictag;

import lombok.*;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagDefault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttributeSpec;
import org.springframework.stereotype.Component;

import java.util.Set;

@Getter
public class MainTopicTag {

    private final String mainValue;

    @Builder
    public MainTopicTag(@NonNull String mainValue) {
        this.mainValue = mainValue;
    }
}
