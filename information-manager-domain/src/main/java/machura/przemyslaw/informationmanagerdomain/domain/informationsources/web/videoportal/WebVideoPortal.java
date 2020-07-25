package machura.przemyslaw.informationmanagerdomain.domain.informationsources.web.videoportal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import machura.przemyslaw.informationmanagerdomain.domain.informationsources.web.WebResource;

@AllArgsConstructor
@Builder
public class WebVideoPortal implements WebResource {
    private final String webDomain;
    private final String resourceUri;
    private final Video video;
    private final WebResourcePublisher webResourcePublisher;
}
