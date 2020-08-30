package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.deprecated;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttr;

import java.util.List;

public interface TagsAttributesExtractor {
    Either<List<Fault>, List<TagAttr>> extractAttributes(String tag);
}
