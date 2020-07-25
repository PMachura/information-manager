package machura.przemyslaw.informationmanagerdomain;

import lombok.RequiredArgsConstructor;
import machura.przemyslaw.informationmanagerdomain.domain.taggeddata.TaggedTextDefault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.maintopictag.MainTopicTag;
import machura.przemyslaw.informationmanagerdomain.persistence.TaggedTextDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class InformationManagerDomainApplication {

    public static void main(String[] args) {
        SpringApplication.run(InformationManagerDomainApplication.class, args);
    }

}

interface TaggedTextRepository extends ReactiveCrudRepository<TaggedTextDAO, String> {
}

@Component
@RequiredArgsConstructor
class DataInitializer {
    private final TaggedTextRepository taggedTextRepository;

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
        return Flux.just(
                TaggedTextDAO.from(
                        TaggedTextDefault.builder()
                                .text("JVM is awesome")
                                .tag(MainTopicTag.builder()
                                        .mainTopicValue("JVM")
                                        .build())
                                .build()),
                TaggedTextDAO.from(
                        TaggedTextDefault.builder()
                                .text("JVM JMM is hard")
                                .tag(MainTopicTag.builder()
                                        .mainTopicValue("JVM")
                                        .build())
                                .build()),
                TaggedTextDAO.from(
                        TaggedTextDefault.builder()
                                .text("Spring is awesome")
                                .tag(MainTopicTag.builder()
                                        .mainTopicValue("Spring")
                                        .build())
                                .build()),
                TaggedTextDAO.from(
                        TaggedTextDefault.builder()
                                .text("Spring is easy")
                                .tag(MainTopicTag.builder()
                                        .mainTopicValue("Spring")
                                        .build())
                                .build())
        );
    }
}
