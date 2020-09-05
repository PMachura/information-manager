package machura.przemyslaw.informationmanagerclient;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
public class HtmlStreamClient {

    @Bean
    RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
        return builder
                .connectTcp("localhost", 7000)
                .block();
    }


    @Bean
    ApplicationListener<ApplicationReadyEvent> client(RSocketRequester requester) {
        return args -> {
            var htmlStream = requester.route("html-stream")
                    .data(new HtmlContentRequest("RSocketRequester"))
                    .retrieveFlux(HtmlContentResopnse.class);
            htmlStream.subscribe(System.out::println);
        };
    }
}
