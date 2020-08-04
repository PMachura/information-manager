package machura.przemyslaw.informationmanagerdomain;

import lombok.RequiredArgsConstructor;
import machura.przemyslaw.informationmanagerdomain.domain.taggeddata.TaggedText;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specified.maintopictag.MainTopicTag;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specified.maintopictag.MainTopicTagSpec;
import machura.przemyslaw.informationmanagerdomain.persistence.tags.TaggedTextDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;

@SpringBootApplication
public class InformationManagerDomainApplication {

    public static void main(String[] args) {
        SpringApplication.run(InformationManagerDomainApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> routes(TaggedTextRepository repository) {
        return RouterFunctions.route()
                .GET("/tags", serverRequest -> ServerResponse.ok().body(repository.findAll(), TaggedTextDAO.class))
                .build();
    }

}

interface TaggedTextRepository extends ReactiveCrudRepository<TaggedTextDAO, String> {
}

@Component
@RequiredArgsConstructor
class DataInitializer {
    private final TaggedTextRepository taggedTextRepository;
    private final MainTopicTagSpec mainTopicTagSpec;

    @EventListener(ApplicationReadyEvent.class)
    public void insertData() {
        Flux<TaggedTextDAO> sampleData = createDataSamples();
        taggedTextRepository.deleteAll()
                .thenMany(sampleData)
                .flatMap(taggedTextRepository::save)
                .thenMany(taggedTextRepository.findAll())
                .subscribe(System.out::println);
    }

    private Flux<TaggedTextDAO> createDataSamples() {
        List<Tuple2<String, String>> textWithMainTopic = List.of(
                Tuples.of("JVM is awesome", "JVM"),
                Tuples.of("JVM JMM is hard", "JVM"),
                Tuples.of("Spring is awesome", "Spring"),
                Tuples.of("Spring is easy", "Spring")
        );

        return Flux.fromIterable(textWithMainTopic)
                .map(textWithMainTopicValue -> TaggedTextDAO.from(TaggedText.builder()
                        .text(textWithMainTopicValue.getT1())
                        .tag(mainTopicTagSpec.map(
                                MainTopicTag.builder()
                                        .mainValue(textWithMainTopicValue.getT2())
                                        .build()))
                        .build()));
    }
}
