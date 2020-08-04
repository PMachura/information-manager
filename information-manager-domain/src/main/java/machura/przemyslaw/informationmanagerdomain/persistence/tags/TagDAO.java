package machura.przemyslaw.informationmanagerdomain.persistence.tags;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "Tag")
@Builder
@Data
public class TagDAO {

    @Id
    String id;

    @NonNull
    private final String name;
    private final String mainValue;
    private final List<TagAttributeDAO> attributes;

    public static TagDAO from(Tag tag) {
        return TagDAO.builder()
                .name(tag.getName())
                .mainValue(tag.getMainValue())
                .attributes(TagAttributeDAO.from(tag.getAttributes()))
                .build();
    }

    public static List<TagDAO> from(Collection<? extends Tag> tags) {
        return tags.stream()
                .map(TagDAO::from)
                .collect(Collectors.toList());
    }
}
