package machura.przemyslaw.informationmanagerdomain.domain.tags.specified.maintopictag;

import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagDefault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttributeSpec;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MainTopicTagSpec implements TagSpec<MainTopicTag> {

    static final String TAG_NAME = "mainTopic";
    private final Set<TagAttributeSpec<?>> attributesRestrictedTo = Set.of();
    private final Set<TagAttributeSpec<?>> requiredAttributes = Set.of();
    private final Set<String> mainValuesRestrictedTo = Set.of();

    @Override
    public Set<TagAttributeSpec<?>> getAttributesRestrictedTo() {
        return attributesRestrictedTo;
    }

    @Override
    public Set<String> getMainValuesRestrictedTo() {
        return mainValuesRestrictedTo;
    }

    @Override
    public Set<TagAttributeSpec<?>> getRequiredAttributes() {
        return requiredAttributes;
    }

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public Tag<MainTopicTag> map(MainTopicTag obj) {
        return TagDefault.<MainTopicTag>builder()
                .mainValue(obj.getMainValue())
                .build();
    }
}
