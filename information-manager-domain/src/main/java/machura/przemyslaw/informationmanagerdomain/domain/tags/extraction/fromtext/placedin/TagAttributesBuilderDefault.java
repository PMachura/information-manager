package machura.przemyslaw.informationmanagerdomain.domain.tags.extraction.fromtext.placedin;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttributeImpl;


public class TagAttributesBuilderDefault implements TagAttributesBuilder {
    @Override
    public Either<Fault, TagAttribute> build(String name, String value) {
        //todo validate building process
        return Either.right(new TagAttributeImpl(name, value));
    }
}
