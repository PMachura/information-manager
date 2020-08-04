package machura.przemyslaw.informationmanagerdomain.domain.tags.specified.datasourcetag.videoportal;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.errors.FaultImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttributeImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttributeSpec;

class DomainUriSpec implements TagAttributeSpec<DomainUri> {

    static final String ATTRIBUTE_NAME = "domainUri";

    @Override
    public String getAttributeName() {
        return ATTRIBUTE_NAME;
    }

    @Override
    public Either<Fault, DomainUri> create(String value){
        return isAttributeValueValid(value) ?
                Either.right(new DomainUri(value))
                : Either.left(FaultImpl.fromReason("Illegal value"));
    }

    @Override
    public TagAttribute map(DomainUri object) {
        return TagAttributeImpl.builder()
                .name(getAttributeName())
                .value(object.getValue())
                .build();
    }
}
