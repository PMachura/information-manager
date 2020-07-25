package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.placedin;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;

public interface TagAttributesBuilder {
     Either<Fault, TagAttribute> build(String name, String value);
}
