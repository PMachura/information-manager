package machura.przemyslaw.informationmanagerdomain.domain.tags.specified.datasourcetag.videoportal;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.errors.FaultImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttribute;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttributeImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttributeSpec;

class MovieNameSpec implements TagAttributeSpec<MovieName> {

    static final String ATTRIBUTE_NAME = "movieName";

    @Override
    public String getAttributeName() {
        return ATTRIBUTE_NAME;
    }

    @Override
    public Either<Fault, MovieName> create(String value){
        return isAttributeValueValid(value) ?
                Either.right(new MovieName(value))
                : Either.left(FaultImpl.fromReason("Illegal value"));
    }

    @Override
    public TagAttribute map(MovieName object) {
        return TagAttributeImpl.builder()
                .name(getAttributeName())
                .value(object.getValue())
                .build();
    }
}
