package machura.przemyslaw.informationmanagerdomain.persistence;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import machura.przemyslaw.informationmanagerdomain.domain.taggeddata.TaggedText;
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

    public static TaggedTextDAO from(TaggedText taggedText) {
        return TaggedTextDAO.builder()
                .text(taggedText.getText())
                .tags(TagDAO.from(taggedText.getTags()))
                .build();
    }
}


