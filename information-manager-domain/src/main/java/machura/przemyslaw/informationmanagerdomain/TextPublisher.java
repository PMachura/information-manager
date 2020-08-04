package machura.przemyslaw.informationmanagerdomain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.*;

@Component
public class TextPublisher {
    private final ApplicationEventPublisher publisher;
    private final ScheduledExecutorService executorService;
    private int issueNumber =  0;

    @Autowired
    public TextPublisher( ApplicationEventPublisher publisher) {
        this.publisher = publisher;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    @PostConstruct
    public void startPublishing(){
        this.executorService.schedule(this::printText, 1, TimeUnit.SECONDS);
    }

    private void printText(){
        String text = "ISSUE:" + issueNumber + " date:" + Instant.now();
        issueNumber++;
        publisher.publishEvent(text);
        executorService.schedule(this::printText, new Random().nextInt(5000), TimeUnit.MILLISECONDS);
    }
}
