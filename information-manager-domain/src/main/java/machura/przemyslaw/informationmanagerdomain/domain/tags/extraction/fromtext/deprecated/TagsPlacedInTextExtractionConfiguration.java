package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.deprecated;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tags.extraction.text.placedin")
@Setter
public class TagsPlacedInTextExtractionConfiguration {

    private String startMarker;
    private String endMarker;
    private String terminationMarker;
    private String attributesNameValueSeparator;
    private String attributesValuesEnclosingMarker;

    @Bean
    public TagsInTextPropertiesSupplier tagsInTextPropertiesSupplier(){
        return TagsInTextPropertiesSupplierDefault.builder()
                .startMarker(startMarker)
                .endMarker(endMarker)
                .terminationMarker(terminationMarker)
                .attributesNameValueSeparator(attributesNameValueSeparator)
                .attributesValuesEnclosingMarker(attributesValuesEnclosingMarker)
                .build();
    }

    @Bean
    public TagAttributesBuilder tagAttributesBuilder(){
        return new TagAttributesBuilderDefault();
    }

    @Bean
    public TagsAttributesExtractor tagsAttributesExtractor(TagsInTextPropertiesSupplier tagsInTextPropertiesSupplier){
        return TagsAttributesExtractorDefault.builder()
                .tagAttributesBuilder(tagAttributesBuilder())
                .tagsInTextPropertiesSupplier(tagsInTextPropertiesSupplier)
                .build();
    }

    @Bean
    FileProcessor fileProcessor(TagsInTextPropertiesSupplier tagsInTextPropertiesSupplier){
       return new FileProcessor(tagsInTextPropertiesSupplier);
    }
}
