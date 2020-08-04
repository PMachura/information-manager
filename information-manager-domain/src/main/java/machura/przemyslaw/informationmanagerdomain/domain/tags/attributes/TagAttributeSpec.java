package machura.przemyslaw.informationmanagerdomain.domain.tags.attributes;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public interface TagAttributeSpec<T> {
    String getAttributeName();

    default Set<String> getValuesRestrictedTo() {
        return Set.of();
    }

    Either<Fault, T> create(String value);

    TagAttribute map(T object);

    default boolean isCompatible(TagAttribute tagAttribute) {
        return !Objects.isNull(tagAttribute)
                && Objects.equals(tagAttribute.getName(), getAttributeName())
                && isAttributeValueValid(tagAttribute.getValue());
    }

    default Optional<String> getDefaultValue() {
        return getValuesRestrictedTo().size() == 1 ?
                getValuesRestrictedTo().stream().findAny()
                : Optional.empty();
    }

    default boolean isAttributeValueValid(String value) {
        if (Objects.isNull(value)) return false;

        Set<String> valuesRestrictedTo = getValuesRestrictedTo();
        if (Objects.isNull(valuesRestrictedTo) || valuesRestrictedTo.isEmpty()) {
            return true;
        }

        return valuesRestrictedTo.contains(value);
    }
}
