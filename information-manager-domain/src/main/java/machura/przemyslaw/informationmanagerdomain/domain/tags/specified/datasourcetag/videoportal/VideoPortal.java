package machura.przemyslaw.informationmanagerdomain.domain.tags.specified.datasourcetag.videoportal;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VideoPortal {

    private final ChannelName channelName;
    private final DomainUri domainUri;
    private final MovieName movieName;

    @Builder(access = AccessLevel.PUBLIC)
    public VideoPortal(String domainURI, String channelName, String movieName) {
        this.domainUri = new DomainUri(domainURI);
        this.channelName = new ChannelName(channelName);
        this.movieName = new MovieName(movieName);
    }
}
