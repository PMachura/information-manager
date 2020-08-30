package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.deprecated;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttr;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrImpl;


public class TagAttributesBuilderDefault implements TagAttributesBuilder {
    @Override
    public Either<Fault, TagAttr> build(String name, String value) {
        //todo validate building process
        return Either.right(new TagAttrImpl(name, value));
    }
}
