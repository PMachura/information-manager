package machura.przemyslaw.informationmanagerdomain.persistence;

import lombok.Builder;
import lombok.Singular;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specified.datasourcetag.videoportal.VideoPortal;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "ViewedYoutubeFilms")
@Builder(builderClassName = "Builder")
public class ViewedYoutubeFilms {
    @Id
    String id;

    @Singular
    private final List<VideoPortal> videoPortalsDataSources;
}
