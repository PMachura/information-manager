package machura.przemyslaw.informationmanagerdomain.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class HtmlPublisher {
    private final ApplicationEventPublisher publisher;
    private final ScheduledExecutorService executorService;
    private int counter =  0;

    @Autowired
    public HtmlPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    //@PostConstruct
    public void startPublishing(){
        this.executorService.schedule(this::publishHtmlText, 1, TimeUnit.SECONDS);
    }

    public static String createHtmlText(int counter){
        return  """
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <title>Title</title>
                        Date: $date 
                        <MainSubject>MainSubject_$counter</MainSubject>
                        <RelatedSubject>RelatedSubject_$counter</RelatedSubject>
                        <InternetVideo DomainURI="youtube.com" ChannelName="SpringDevelopers" VideoName="SpringTips"/>
                    </head>
                    <body>
                                
                    </body>
                </html>
                """
                .replace("$counter", String.valueOf(counter))
                .replace("$date", Instant.now().toString());
    }

    private void publishHtmlText(){
        String htmlText = createHtmlText(counter);
        System.out.println("Publishing: " + counter);
        publisher.publishEvent(new HtmlContent(htmlText));
        counter++;
        executorService.schedule(this::publishHtmlText, new Random().nextInt(5000), TimeUnit.MILLISECONDS);
    }
}
