package machura.przemyslaw.informationmanagerdomain;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;
import machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.placedin.FileProcessor;
import machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.placedin.TagsAttributesExtractor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class InformationManagerDomainApplicationTests {

    static class TagWithAttributes {
        final String tag;
        final Map<String, String> attributes;

        public static Builder builder(String tag) {
            return new Builder(tag);
        }

        public TagWithAttributes(String tag, Map<String, String> attributes) {
            this.tag = tag;
            this.attributes = attributes;
        }

        public String getTag() {
            return tag;
        }

        public Map<String, String> getAttributes() {
            return attributes;
        }

        public static class Builder {
            String tag;
            Map<String, String> attributes = new HashMap<>();

            public Builder(String tag) {
                this.tag = tag;
            }

            public Builder addAttribute(String name, String value) {
                attributes.put(name, value);
                return this;
            }

            public TagWithAttributes build() {
                return new TagWithAttributes(tag, attributes);
            }
        }
    }

    @Autowired
    TagsAttributesExtractor tagsAttributesExtractor;

    @Autowired
    FileProcessor fileProcessor;


    @Test
    void attributesExtractionTest() {
        String tag = "<firstAttr=\"1\" secondAttr=\"2\"/>";
        TagWithAttributes tagWithAttributes = TagWithAttributes.builder(tag)
                .addAttribute("firstAttr", "1")
                .addAttribute("secondAttr", "2")
                .build();
        Map<String, String> expectedAttributesNameValue = tagWithAttributes.getAttributes();

        Either<List<Fault>, List<TagAttribute>> extractionResult = tagsAttributesExtractor.extractAttributes(tag);

        Assertions.assertThat(extractionResult.isRight()).isTrue();

        List<TagAttribute> extractedAttributes = extractionResult.get();
        Assertions.assertThat(extractedAttributes.size()).isEqualTo(expectedAttributesNameValue.size());

        for (TagAttribute tagAttribute : extractedAttributes) {
            Assertions.assertThat(expectedAttributesNameValue.containsKey(tagAttribute.getName()));
            Assertions.assertThat(tagAttribute.getValue()).isEqualTo(expectedAttributesNameValue.get(tagAttribute.getName()));
        }
    }

    @Test
    void fileProcessorTest(){
        String tag0 = "<firstAttr=\"01\" secondAttr=\"02\"/>";
        String tag1 = "<firstAttr=\"11\" secondAttr=\"12\"/>";
        String text = "hello" + tag0 + tag1 + "good bye";

        List<String> extractedTags = fileProcessor.extractRawTags(text);
        System.out.println(extractedTags);

        Assertions.assertThat(extractedTags.size()).isEqualTo(2);
        Assertions.assertThat(extractedTags.get(0)).isEqualTo(tag0);
        Assertions.assertThat(extractedTags.get(1)).isEqualTo(tag1);
    }

}
