package machura.przemyslaw.informationmanagerdomain.domain.tags;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;

import java.util.Objects;
import java.util.Set;

public interface TagSpec<T> {

    String getTagName();

    Set<TagAttrSpec<?>> getSupportedAttr();

    Set<TagAttrSpec<?>> getRequiredAttr();

    boolean isTextRequired();

    Tag toTag(T obj);

    Either<Fault, T> toObj(Tag tag);

    default boolean isCompatible(Tag tag) {
        if (!Objects.equals(getTagName(), tag.getName())) {
            return false;
        }

        if (isTextRequired()
                && (Objects.isNull(tag.getText()) || tag.getText().isBlank())) {
            return false;
        }

        return areRequiredAttrsPresent(tag.getAttributes());
    }

    default boolean areRequiredAttrsPresent(Set<TagAttr> givenAttrs) {
        return getRequiredAttr().stream()
                .allMatch(requiredTagAttrSpec -> givenAttrs.stream().anyMatch(requiredTagAttrSpec::isCompatible));
    }

    default boolean isCompatible(TagAttr tagAttr) {
        if (Objects.isNull(tagAttr)) return false;

        Set<TagAttrSpec<?>> tagsAttributesSpecsRestrictedTo = getSupportedAttr();
        if (Objects.isNull(tagsAttributesSpecsRestrictedTo) || tagsAttributesSpecsRestrictedTo.isEmpty()) {
            return true;
        }

        return tagsAttributesSpecsRestrictedTo
                .stream()
                .anyMatch(tagAttributeSpec -> tagAttributeSpec.isCompatible(tagAttr));
    }
}

