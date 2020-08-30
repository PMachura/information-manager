package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.errors.FaultImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagSpec;
import org.jsoup.Jsoup;
import org.jsoup.helper.DataUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class TagsExtractorImpl implements TagsExtractor {

    private static final String CHARSET = "UTF-8";
    private final Collection<? extends TagSpec<?>> tagsSpecs;

    @Override
    public List<Either<Fault, Tag>> extract(String text) {
        return extract(Jsoup.parse(text, "", createCaseSensitiveHtmlParser()));
    }

    @Override
    public List<Either<Fault, Tag>> extract(File file) {
        Parser parser = createCaseSensitiveHtmlParser();
        return Try.of(() -> DataUtil.load(new FileInputStream(file), CHARSET, file.getAbsolutePath(), createCaseSensitiveHtmlParser()))
                .map(this::extract)
                .getOrElseGet(throwable -> List.of(Either.left(FaultImpl.fromReason(throwable.getMessage()))));
    }

    private List<Either<Fault, Tag>> extract(Document document) {
        return tagsSpecs.stream()
                .map(tagSpec -> toTag(tagSpec, document))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<Either<Fault, Tag>> toTag(TagSpec<?> tagSpec, Document document) {
        return document.getElementsByTag(tagSpec.getTagName()).stream()
                .map(TagImpl::from)
                .map(tag -> tagSpec.isCompatible(tag) ? Either.<Fault, Tag>right(tag) : Either.<Fault, Tag>left(FaultImpl.fromReason("Incompatible tag: " + tag.getName())))
                .collect(Collectors.toList());
    }

    private static Parser createCaseSensitiveHtmlParser() {
        Parser parser = Parser.htmlParser();
        parser.settings(new ParseSettings(true, true));
        return parser;
    }
}
