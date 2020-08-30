package machura.przemyslaw.informationmanagerdomain.domain.datadescriptors;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class RelatedSubject {
    @NonNull
    private final String relatedSubjectName;
}
