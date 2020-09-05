package machura.przemyslaw.informationmanagerclient;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.rsocket.ClientRSocketConnector;
import org.springframework.integration.rsocket.RSocketInteractionModel;
import org.springframework.integration.rsocket.dsl.RSockets;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Flux;

import java.io.File;

@SpringBootApplication
public class InformationManagerClientApplication {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(InformationManagerClientApplication.class, args);
        System.in.read();
    }

    @Bean
    MessageChannel reactiveMessageChannel() {
        return MessageChannels.flux().get();
    }

    @Bean
    ClientRSocketConnector clientRSocketConnector(RSocketStrategies rSocketStrategies) {
        var clc = new ClientRSocketConnector("localhost", 7000);
        clc.setRSocketStrategies(rSocketStrategies);
        return clc;
    }

    @Bean
    IntegrationFlow rsocketFlow(ClientRSocketConnector clientRSocketConnector,
                                @Value("${directory.home}") File homeDir) {
        var folder = new File(new File(homeDir, "Desktop"), "in");
        var fileInboundAdapter = Files.inboundAdapter(folder)
                .autoCreateDirectory(true)
                .get();

        var rsocket = RSockets.outboundGateway("html-stream")
                .interactionModel(RSocketInteractionModel.requestStream)
                .clientRSocketConnector(clientRSocketConnector)
                .expectedResponseType(HtmlContentResopnse.class);

        return IntegrationFlows
                .from(fileInboundAdapter,
                        pollingChannelAdapter -> pollingChannelAdapter.poller(pollerSpec -> pollerSpec.fixedRate(1_000)))
                .transform(new FileToStringTransformer())
                .transform(String.class, str -> new HtmlContentRequest(str.trim()))
                .handle(rsocket)
                .split()
                .channel(reactiveMessageChannel())
                .handle((GenericHandler<HtmlContentResopnse>) (htmlContentDTO, messageHeaders) -> {
                    System.out.println(htmlContentDTO.toString());
                    return null; //stop processing
                })
                .get();
    }
}
