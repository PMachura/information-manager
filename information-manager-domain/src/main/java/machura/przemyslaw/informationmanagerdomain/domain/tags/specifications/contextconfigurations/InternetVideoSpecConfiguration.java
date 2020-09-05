package machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.contextconfigurations;

import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet.DomainURISpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet.ResourceURISpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet.video.ChannelNameSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet.video.InternetVideoSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet.video.VideoNameSpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InternetVideoSpecConfiguration {

    DomainURISpec domainUriSpec() {
        return DomainURISpec.create();
    }

    ChannelNameSpec channelNameSpec() {
        return ChannelNameSpec.create();
    }

    VideoNameSpec videoNameSpec() {
        return VideoNameSpec.create();
    }

    ResourceURISpec resourceURISpec() {
        return ResourceURISpec.create();
    }

    @Bean
    public InternetVideoSpec internetVideoSpec(@Value("${tags.internetvideo.name}") String internetVideoTagName) {
        return InternetVideoSpec.builder()
                .tagName(internetVideoTagName)
                .domainUriSpec(domainUriSpec())
                .channelNameSpec(channelNameSpec())
                .videoNameSpec(videoNameSpec())
                .resourceURISpec(resourceURISpec())
                .build();
    }

}
