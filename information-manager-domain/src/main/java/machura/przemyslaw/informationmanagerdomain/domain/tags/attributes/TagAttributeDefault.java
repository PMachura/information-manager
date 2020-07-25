package machura.przemyslaw.informationmanagerdomain.domain.tags.attributes;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder(builderClassName = "builder")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TagAttributeDefault implements TagAttribute {
    private final String name;
    private final String value;
}
