package machura.przemyslaw.informationmanagerdomain.publishers;

import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.video.InternetVideo;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.MainSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.*;

@Component
public class VideoPublisher {
    private final ApplicationEventPublisher publisher;
    private final ScheduledExecutorService executorService;
    private int episodeNumber =  0;

    @Autowired
    public VideoPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    //@PostConstruct
    public void startPublishing(){
        this.executorService.schedule(this::publishMovie, 1, TimeUnit.SECONDS);
    }

    private void publishMovie(){
        publisher.publishEvent(new InternetVideoDescription(createVideoData(), new MainSubject("Spring")));
        episodeNumber++;
        executorService.schedule(this::publishMovie, new Random().nextInt(5000), TimeUnit.MILLISECONDS);
    }

    private InternetVideo createVideoData(){
        return InternetVideo.rawBuilder()
                .channelName("SpringDevelopers")
                .domainURI("youtube.com")
                .movieName("Spring tips: " + episodeNumber)
                .build();
    }
}
