package machura.przemyslaw.informationmanagerdomain.domain.tags;

import java.util.Set;

public interface Tag {
    String getName();
    String getText();
    Set<TagAttr> getAttributes();
}
