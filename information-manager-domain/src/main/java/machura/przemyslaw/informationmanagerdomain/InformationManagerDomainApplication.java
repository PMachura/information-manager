package machura.przemyslaw.informationmanagerdomain;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.scheduling.annotation.AsyncConfigurer;

@SpringBootApplication
public class InformationManagerDomainApplication implements AsyncConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(InformationManagerDomainApplication.class, args);
    }
}

