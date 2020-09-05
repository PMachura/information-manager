package machura.przemyslaw.informationmanagerdomain.domain.tags;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

@RequiredArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class TagAttrSpecImpl<T> implements TagAttrSpec<T> {

    @Singular("valueRestrictedTo")
    private final Set<String> valuesRestrictedTo;
    private final String attributeKey;
    private final Function<String, T> attrValueToObjectMapper;
    private final Function<TagAttr, T> tagAttrToObjectMapper;
    private final Function<T, String> objToAttrValueMapper;


    @Override
    public Optional<T> firstMatchFrom(Collection<TagAttr> tagAttrs) {
        return tagAttrs.stream()
                .map(this::fromTagAttr)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    @Override
    public boolean isCompatible(TagAttr tagAttr) {
        return !Objects.isNull(tagAttr)
                && Objects.equals(tagAttr.getKey(), attributeKey)
                && isAttrValueValid(tagAttr.getValue());
    }

    @Override
    public boolean isAttrValueValid(String givenValue) {
        if (Objects.isNull(givenValue)) return false;
        if (Objects.isNull(valuesRestrictedTo) || valuesRestrictedTo.isEmpty()) {
            return true;
        }

        return valuesRestrictedTo.contains(givenValue);
    }

    @Override
    public String getAttributeKey() {
        return attributeKey;
    }

    @Override
    public Optional<T> fromValue(String value) {
        return isAttrValueValid(value) ?
                Optional.of(attrValueToObjectMapper.apply(value)) : Optional.empty();
    }

    @Override
    public Optional<T> fromTagAttr(TagAttr tagAttr) {
        return isCompatible(tagAttr) ?
                Optional.of(tagAttrToObjectMapper.apply(tagAttr)) : Optional.empty();
    }

    @Override
    public TagAttr toTagAttr(T object) {
        return TagAttrImpl.builder()
                .key(getAttributeKey())
                .value(objToAttrValueMapper.apply(object))
                .build();
    }

    @Override
    public String toAttrValue(T object) {
        return objToAttrValueMapper.apply(object);
    }

    @Override
    public Set<String> getValuesRestrictedTo() {
        return valuesRestrictedTo;
    }
}
