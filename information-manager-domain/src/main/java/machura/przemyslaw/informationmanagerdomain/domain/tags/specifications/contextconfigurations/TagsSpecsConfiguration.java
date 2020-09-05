package machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.contextconfigurations;

import machura.przemyslaw.informationmanagerdomain.domain.tags.TagSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet.video.InternetVideoSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.mainsubject.MainSubjectSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.relatedsubjects.RelatedSubjectSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan
public class TagsSpecsConfiguration {

    @Bean
    @Autowired
    public List<TagSpec<?>> tagsSpecs(InternetVideoSpec internetVideoSpec,
                                      MainSubjectSpec mainSubjectTagSpec,
                                      RelatedSubjectSpec relatedSubjectSpec) {
        return List.of(mainSubjectTagSpec, internetVideoSpec, relatedSubjectSpec);
    }
}
