package machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.video;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.DomainURI;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.ResourceURI;

@Getter
@ToString
public class InternetVideo {

    private final DomainURI domainUri;
    private final ResourceURI resourceURI;
    private final ChannelName channelName;
    private final VideoName videoName;

    @Builder(access = AccessLevel.PUBLIC)
    public InternetVideo(DomainURI domainURI, ResourceURI resourceURI, ChannelName channelName, VideoName videoName) {
        this.resourceURI = resourceURI;
        this.domainUri = domainURI;
        this.channelName = channelName;
        this.videoName = videoName;
    }

    @Builder(access = AccessLevel.PUBLIC, builderMethodName = "rawBuilder", builderClassName = "RawBuilder")
    public InternetVideo(String domainURI, String resourceURI, String channelName, String movieName) {
        this.domainUri = new DomainURI(domainURI);
        this.resourceURI = new ResourceURI(resourceURI);
        this.channelName = new ChannelName(channelName);
        this.videoName = new VideoName(movieName);
    }
}
