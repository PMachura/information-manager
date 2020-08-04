package machura.przemyslaw.informationmanagerdomain.domain.tags.attributes;

import lombok.Builder;

import java.util.Set;

@Builder
public class TagAttributeSpecDefault  {
    private final String name;
    private final Set<String> valuesRestrictedTo;
}
