package machura.przemyslaw.informationmanagerdomain.domain.tags.specified.datasourcetag.videoportal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class DomainUri {
    private final String value;
}
