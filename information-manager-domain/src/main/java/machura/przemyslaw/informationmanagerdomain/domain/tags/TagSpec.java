package machura.przemyslaw.informationmanagerdomain.domain.tags;

import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttributeSpec;

import java.util.Objects;
import java.util.Set;

public interface TagSpec<T> {

    String getTagName();

    Set<TagAttributeSpec<?>> getAttributesRestrictedTo();

    Set<String> getMainValuesRestrictedTo();

    Set<TagAttributeSpec<?>> getRequiredAttributes();

    Tag<T> map(T obj);

    default boolean isMainValueCandidateValid(String mainValue) {
        if (Objects.isNull(mainValue)) return false;

        Set<String> mainValuesRestrictedTo = getMainValuesRestrictedTo();
        if (Objects.isNull(mainValuesRestrictedTo) || mainValuesRestrictedTo.isEmpty()) {
            return true;
        }
        return mainValuesRestrictedTo.contains(mainValue);
    }

    default boolean isCompatible(TagAttribute tagAttribute) {
        if(Objects.isNull(tagAttribute)) return false;

        Set<TagAttributeSpec<?>> tagsAttributesSpecsRestrictedTo = getAttributesRestrictedTo();
        if (Objects.isNull(tagsAttributesSpecsRestrictedTo) || tagsAttributesSpecsRestrictedTo.isEmpty()) {
            return true;
        }

        return tagsAttributesSpecsRestrictedTo
                .stream()
                .anyMatch(tagAttributeSpec -> tagAttributeSpec.isCompatible(tagAttribute));
    }
}

