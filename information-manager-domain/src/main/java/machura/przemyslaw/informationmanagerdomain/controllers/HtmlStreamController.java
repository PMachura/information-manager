package machura.przemyslaw.informationmanagerdomain.controllers;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;
import machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.TagsExtractor;
import machura.przemyslaw.informationmanagerdomain.persistence.dao.InternetVideoDescriptionDAO;
import machura.przemyslaw.informationmanagerdomain.persistence.repository.InternetVideoDescriptionRepository;
import machura.przemyslaw.informationmanagerdomain.publishers.HtmlEvent;
import machura.przemyslaw.informationmanagerdomain.publishers.InternetVideoDescription;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
@RequiredArgsConstructor
public class HtmlStreamController {

    private final Set<SseEmitter> subscribers = new CopyOnWriteArraySet<>();
    private final TagsExtractor tagsExtractor;

    @RequestMapping(value = "/html-stream", method = RequestMethod.GET)
    public SseEmitter publishedText(HttpServletRequest httpServletRequest){
        SseEmitter emitter = new SseEmitter();
        subscribers.add(emitter);
        emitter.onTimeout(() -> subscribers.remove(emitter));
        emitter.onCompletion(() -> subscribers.remove(emitter));
        return emitter;
    }

    @Async
    @EventListener
    public void handlePublishedVideo(HtmlEvent htmlEvent){
        System.out.println("Published html tags");
        List<Either<Fault, Tag>> tags = tagsExtractor.extract(htmlEvent.getHtmlText());
        tags.forEach(result -> result.peek(System.out::println));
        List<SseEmitter> notActiveEmitters = new ArrayList<>();
        subscribers.forEach(emitter -> {
            try {
                emitter.send(htmlEvent);
            } catch (Exception e){
                notActiveEmitters.add(emitter);
            }
        });
        subscribers.removeAll(notActiveEmitters);
    }
}
