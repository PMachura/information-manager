package machura.przemyslaw.informationmanagerdomain.controllers;

import lombok.AllArgsConstructor;
import machura.przemyslaw.informationmanagerdomain.publishers.InternetVideoDescription;
import machura.przemyslaw.informationmanagerdomain.persistence.dao.InternetVideoDescriptionDAO;
import machura.przemyslaw.informationmanagerdomain.persistence.repository.InternetVideoDescriptionRepository;
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
@AllArgsConstructor
public class VideoController {
    private final Set<SseEmitter> subscribers = new CopyOnWriteArraySet<>();
    private final InternetVideoDescriptionRepository internetVideoDescriptionRepository;

    @RequestMapping(value = "/video-stream", method = RequestMethod.GET)
    public SseEmitter publishedText(HttpServletRequest httpServletRequest){
        SseEmitter emitter = new SseEmitter();
        subscribers.add(emitter);
        emitter.onTimeout(() -> subscribers.remove(emitter));
        emitter.onCompletion(() -> subscribers.remove(emitter));
        return emitter;
    }

    @Async
    @EventListener
    public void handlePublishedVideo(InternetVideoDescription internetVideoDescription){
        System.out.println(internetVideoDescription);
        internetVideoDescriptionRepository.save(InternetVideoDescriptionDAO.from(internetVideoDescription))
                .subscribe(dao -> System.out.println("Saved: " + dao));
        List<SseEmitter> notActiveEmitters = new ArrayList<>();
        subscribers.forEach(emitter -> {
            try {
                emitter.send(internetVideoDescription);
            } catch (Exception e){
                notActiveEmitters.add(emitter);
            }
        });
        subscribers.removeAll(notActiveEmitters);
    }
}
