package machura.przemyslaw.informationmanagerdomain.domain.tags.specified.datasourcetag.videoportal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoPortalDataSourceConfiguration {

    DomainUriSpec domainUriSpec(){
        return new DomainUriSpec();
    }

    ChannelNameSpec channelNameSpec(){
        return new ChannelNameSpec();
    }

    MovieNameSpec movieNameSpec(){
        return new MovieNameSpec();
    }

    @Bean
    public VideoPortalDataSourceSpec videoPortalDataSourceSpec(){
        return VideoPortalDataSourceSpec.builder()
                .domainUriSpec(domainUriSpec())
                .channelNameSpec(channelNameSpec())
                .movieNameSpec(movieNameSpec())
                .build();
    }

}
