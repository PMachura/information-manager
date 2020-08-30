package machura.przemyslaw.informationmanagerdomain.persistence.dao.tags;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import machura.przemyslaw.informationmanagerdomain.domain.taggeddata.TaggedData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "TaggedText")
@Builder(builderClassName = "Builder")
@Data
public class TaggedTextDAO {
    @Id
    String id;

    @NonNull
    private String text;
    @Singular
    private List<TagDAO> tags;

    public static TaggedTextDAO from(TaggedData<String> taggedData) {
        return TaggedTextDAO.builder()
                .text(taggedData.getData())
                .tags(TagDAO.from(taggedData.getTags()))
                .build();
    }
}


