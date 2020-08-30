package machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.contextconfigurations;

import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.relatedsubjects.RelatedSubjectSpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RelatedSubjectSpecConfiguration {

    @Bean
    public RelatedSubjectSpec relatedSubjectSpec(@Value("${tags.relatedsubject.name}") String relatedSubjectTagName){
        return new RelatedSubjectSpec(relatedSubjectTagName);
    }
}
