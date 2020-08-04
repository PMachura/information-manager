package machura.przemyslaw.informationmanagerdomain.domain.tags;

import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;

import java.util.Set;

public interface Tag<T> {
    String getName();
    String getMainValue();
    Set<TagAttribute> getAttributes();
}
