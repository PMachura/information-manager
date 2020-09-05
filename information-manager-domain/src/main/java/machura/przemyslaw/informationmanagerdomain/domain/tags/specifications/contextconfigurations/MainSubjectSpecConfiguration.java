package machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.contextconfigurations;

import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.mainsubject.MainSubjectSpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainSubjectSpecConfiguration {

    @Bean
    public MainSubjectSpec mainSubjectTagSpec(@Value("${tags.mainsubject.name}") String mainSubjectTagName){
        return new MainSubjectSpec(mainSubjectTagName);
    }
}
