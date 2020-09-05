package machura.przemyslaw.informationmanagerdomain.publishers;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.video.InternetVideo;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.MainSubject;

@RequiredArgsConstructor
@Builder
@Data
public final class InternetVideoDescription {
    private final InternetVideo movie;
    private final MainSubject mainSubject;
}
