package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.contextconfigurations;

import lombok.RequiredArgsConstructor;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.TagsExtractor;
import machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.TagsExtractorImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.contextconfigurations.TagsSpecsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@Import({TagsSpecsConfiguration.class})
public class TagsFromTextExtractorConfiguration {

    private final List<TagSpec<?>> tagsSpecs;

    @Bean
    public TagsExtractor tagsExtractor() {
        return new TagsExtractorImpl(tagsSpecs);
    }
}
