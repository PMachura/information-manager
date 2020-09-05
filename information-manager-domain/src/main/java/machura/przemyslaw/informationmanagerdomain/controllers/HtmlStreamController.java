package machura.przemyslaw.informationmanagerdomain.controllers;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import machura.przemyslaw.informationmanagerdomain.controllers.dto.HtmlContentResopnse;
import machura.przemyslaw.informationmanagerdomain.controllers.dto.HtmlContentRequest;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;
import machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.TagsExtractor;
import machura.przemyslaw.informationmanagerdomain.publishers.HtmlContent;
import machura.przemyslaw.informationmanagerdomain.publishers.HtmlPublisher;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class HtmlStreamController {

    private final Set<SseEmitter> subscribers = new CopyOnWriteArraySet<>();
    private final TagsExtractor tagsExtractor;

    @MessageMapping("html-stream")
    public Flux<HtmlContentResopnse> publishedText(HtmlContentRequest htmlContentRequest){
        var stream = IntStream.range(0, Integer.MAX_VALUE)
                .boxed()
                .map(HtmlPublisher::createHtmlText)
                .map(htmlText -> htmlContentRequest.getMsg() + "\n" + htmlText)
                .map(HtmlContentResopnse::new);
        return Flux.fromStream(stream)
                .delayElements(Duration.ofSeconds(1));
    }

    //@EventListener todo
    public void handlePublishedVideo(HtmlContent htmlContent){
        System.out.println("Published html tags");
        List<Either<Fault, Tag>> tags = tagsExtractor.extract(htmlContent.getHtmlText());
        tags.forEach(result -> result.peek(System.out::println));
        List<SseEmitter> notActiveEmitters = new ArrayList<>();
        subscribers.forEach(emitter -> {
            try {
                emitter.send(htmlContent);
            } catch (Exception e){
                notActiveEmitters.add(emitter);
            }
        });
        subscribers.removeAll(notActiveEmitters);
    }
}
