package machura.przemyslaw.informationmanagerdomain;

import lombok.RequiredArgsConstructor;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.MainSubject;
import machura.przemyslaw.informationmanagerdomain.domain.taggeddata.TaggedText;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.mainsubject.MainSubjectSpec;
import machura.przemyslaw.informationmanagerdomain.persistence.dao.tags.TaggedTextDAO;
import machura.przemyslaw.informationmanagerdomain.persistence.repository.TaggedTextRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;

@Component
@RequiredArgsConstructor
class DataInitializer {
    private final TaggedTextRepository taggedTextRepository;
    private final MainSubjectSpec mainSubjectTagSpec;

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
        List<Tuple2<String, String>> textWithMainSubject = List.of(
                Tuples.of("JVM is awesome", "JVM"),
                Tuples.of("JVM JMM is hard", "JVM"),
                Tuples.of("Spring is awesome", "Spring"),
                Tuples.of("Spring is easy", "Spring")
        );

        return Flux.fromIterable(textWithMainSubject)
                .map(textWithMainSubjectValue -> TaggedTextDAO.from(TaggedText.builder()
                        .text(textWithMainSubjectValue.getT1())
                        .tag(mainSubjectTagSpec.toTag(
                                MainSubject.builder()
                                        .mainSubjectName(textWithMainSubjectValue.getT2())
                                        .build()))
                        .build()));
    }
}
