package machura.przemyslaw.informationmanagerdomain.domain.tags;

import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;

import java.util.List;
import java.util.Optional;

public interface Tag {
    String getName();
    Optional<String> getMainValue();
    List<? extends TagAttribute> getAttributes();
}
