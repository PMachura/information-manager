package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.deprecated;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttr;

public interface TagAttributesBuilder {
     Either<Fault, TagAttr> build(String name, String value);
}
