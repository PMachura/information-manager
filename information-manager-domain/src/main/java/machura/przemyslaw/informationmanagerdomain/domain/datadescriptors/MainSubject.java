package machura.przemyslaw.informationmanagerdomain.domain.datadescriptors;

import lombok.*;


@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(access = AccessLevel.PUBLIC)
@Getter
public class MainSubject {
    @NonNull
    private final String mainSubjectName;
}

