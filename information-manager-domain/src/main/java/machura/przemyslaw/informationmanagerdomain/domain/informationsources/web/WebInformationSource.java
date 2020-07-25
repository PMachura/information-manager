package machura.przemyslaw.informationmanagerdomain.domain.informationsources.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import machura.przemyslaw.informationmanagerdomain.domain.informationsources.InformationSource;

@AllArgsConstructor
@Builder
public class WebInformationSource implements InformationSource {
    private static final InformationSourceType INFORMATION_SOURCE_TYPE = InformationSourceType.WEB;
    private final WebResource webResource;
}
