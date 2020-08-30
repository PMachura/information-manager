package machura.przemyslaw.informationmanagerdomain;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;
import machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.contextconfigurations.TagsFromTextExtractorConfiguration;
import machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.TagsExtractor;
import org.apache.catalina.core.ApplicationContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.File;
import java.util.List;

@SpringJUnitConfig({TagsFromTextExtractorConfiguration.class})
@TestPropertySource(locations="classpath:application.properties")
public class TagsExtractionTest {

    private final String TEXT_FILE_FOLDER_PATH = "src/test/resources/tags/extraction/fromtext/";

    @Autowired
    private TagsExtractor tagsExtractor;

    @Test
    public void shouldExtractTags() {
        getFile("forTagsExtractionTest.html");
        List<Either<Fault, Tag>> extractedTags = tagsExtractor.extract(getFile("forTagsExtractionTest.html"));
        Assertions.assertThat(extractedTags.size()).isEqualTo(3);
        Assertions.assertThat(extractedTags).allMatch(Either::isRight);
    }

    private File getFile(String filename) {
        File file = new File(TEXT_FILE_FOLDER_PATH + filename);
        if (!file.exists()) throw new IllegalStateException("Cannot load file: " + filename);
        return file;
    }
}
