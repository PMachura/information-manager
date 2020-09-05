package machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet;

import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.DomainURI;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttr;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpecImpl;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class DomainURISpec implements TagAttrSpec<DomainURI> {

    private final TagAttrSpecImpl<DomainURI> tagAttrSpec;
    private final Function<String, DomainURI> attrValueToObjectMapper = DomainURI::new;
    private final Function<TagAttr, DomainURI> tagAttrToObjectMapper = tagAttr -> new DomainURI(tagAttr.getValue());
    private final Function<DomainURI, String> objToAttrValueMapper = DomainURI::getValue;

    public DomainURISpec(String attrKey, Set<String> valuesRestrictedTo) {
        tagAttrSpec = TagAttrSpecImpl.<DomainURI>builder()
                .attributeKey(attrKey)
                .valuesRestrictedTo(valuesRestrictedTo)
                .attrValueToObjectMapper(attrValueToObjectMapper)
                .tagAttrToObjectMapper(tagAttrToObjectMapper)
                .objToAttrValueMapper(objToAttrValueMapper)
                .build();

    }

    public static DomainURISpec create(){
        return new DomainURISpec(DomainURI.class.getSimpleName(), Set.of());
    }

    @Override
    public String getAttributeKey() {
        return tagAttrSpec.getAttributeKey();
    }

    @Override
    public Set<String> getValuesRestrictedTo() {
        return tagAttrSpec.getValuesRestrictedTo();
    }

    @Override
    public boolean isCompatible(TagAttr tagAttr) {
        return tagAttrSpec.isCompatible(tagAttr);
    }

    @Override
    public boolean isAttrValueValid(String value) {
        return tagAttrSpec.isAttrValueValid(value);
    }

    @Override
    public Optional<DomainURI> fromValue(String value) {
        return tagAttrSpec.fromValue(value);
    }

    @Override
    public Optional<DomainURI> fromTagAttr(TagAttr tagAttr) {
        return tagAttrSpec.fromTagAttr(tagAttr);
    }

    @Override
    public TagAttr toTagAttr(DomainURI object) {
        return tagAttrSpec.toTagAttr(object);
    }

    @Override
    public String toAttrValue(DomainURI object) {
        return tagAttrSpec.toAttrValue(object);
    }

    @Override
    public Optional<DomainURI> firstMatchFrom(Collection<TagAttr> tagAttrs) {
        return tagAttrSpec.firstMatchFrom(tagAttrs);
    }
}
