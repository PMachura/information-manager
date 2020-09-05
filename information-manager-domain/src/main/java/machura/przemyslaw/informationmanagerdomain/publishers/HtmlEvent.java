package machura.przemyslaw.informationmanagerdomain.publishers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HtmlEvent {
    private final String htmlText;
}
