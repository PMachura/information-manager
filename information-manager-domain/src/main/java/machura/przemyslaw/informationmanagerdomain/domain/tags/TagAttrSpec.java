package machura.przemyslaw.informationmanagerdomain.domain.tags;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public interface TagAttrSpec<T> {
    Optional<T> firstMatchFrom(Collection<TagAttr> tagAttrs);

    boolean isCompatible(TagAttr tagAttr);

    boolean isAttrValueValid(String value);

    String getAttributeKey();

    Optional<T> fromValue(String value);

    Optional<T> fromTagAttr(TagAttr tagAttr);

    TagAttr toTagAttr(T object);

    String toAttrValue(T object);

    Set<String> getValuesRestrictedTo();


}
