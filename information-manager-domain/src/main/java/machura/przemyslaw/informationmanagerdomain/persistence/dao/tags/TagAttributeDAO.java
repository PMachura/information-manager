package machura.przemyslaw.informationmanagerdomain.persistence.dao.tags;

import lombok.Builder;
import lombok.Data;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttr;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class TagAttributeDAO {
    private final String name;
    private final String value;

    public static TagAttributeDAO from(TagAttr tagAttr) {
        return TagAttributeDAO.builder()
                .name(tagAttr.getKey())
                .value(tagAttr.getValue())
                .build();
    }

    public static List<TagAttributeDAO> from(Collection<? extends TagAttr> tagAttributeCollection) {
        return tagAttributeCollection.stream()
                .map(TagAttributeDAO::from)
                .collect(Collectors.toList());
    }
}
