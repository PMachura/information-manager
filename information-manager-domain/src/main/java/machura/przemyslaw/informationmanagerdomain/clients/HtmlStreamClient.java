package machura.przemyslaw.informationmanagerdomain.clients;

import machura.przemyslaw.informationmanagerdomain.controllers.HtmlStreamController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

@Configuration
@AutoConfigureAfter(HtmlStreamController.class)
public class HtmlStreamClient {




}
