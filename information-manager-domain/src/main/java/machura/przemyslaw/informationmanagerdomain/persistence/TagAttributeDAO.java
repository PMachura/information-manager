package machura.przemyslaw.informationmanagerdomain.persistence;

import lombok.Builder;
import lombok.Data;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class TagAttributeDAO {
    private final String name;
    private final String value;

    public static TagAttributeDAO from(TagAttribute tagAttribute) {
        return TagAttributeDAO.builder()
                .name(tagAttribute.getName())
                .value(tagAttribute.getValue())
                .build();
    }

    public static List<TagAttributeDAO> from(Collection<? extends TagAttribute> tagAttributeCollection) {
        return tagAttributeCollection.stream()
                .map(TagAttributeDAO::from)
                .collect(Collectors.toList());
    }
}
