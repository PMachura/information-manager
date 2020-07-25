package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.placedin;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;

import java.util.List;

public interface TagsAttributesExtractor {
    Either<List<Fault>, List<TagAttribute>> extractAttributes(String tag);
}
