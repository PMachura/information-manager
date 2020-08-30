package machura.przemyslaw.informationmanagerdomain.controllers;

import machura.przemyslaw.informationmanagerdomain.persistence.dao.tags.TaggedTextDAO;
import machura.przemyslaw.informationmanagerdomain.persistence.repository.TaggedTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Controller
public class TagController {

    private final TaggedTextRepository taggedTextRepository;

    @Autowired
    public TagController(TaggedTextRepository taggedTextRepository){
        this.taggedTextRepository = taggedTextRepository;
    }

    @Bean
    RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                .GET("/tags", serverRequest -> ServerResponse.ok().body(taggedTextRepository.findAll(), TaggedTextDAO.class))
                .build();
    }

}
