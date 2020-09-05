package machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet;

import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.DomainURI;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.ResourceURI;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttr;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpecImpl;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class ResourceURISpec implements TagAttrSpec<ResourceURI> {

    private final TagAttrSpecImpl<ResourceURI> tagAttrSpec;
    private final Function<String, ResourceURI> attrValueToObjectMapper = ResourceURI::new;
    private final Function<TagAttr, ResourceURI> tagAttrToObjectMapper = tagAttr -> new ResourceURI(tagAttr.getValue());
    private final Function<ResourceURI, String> objToAttrValueMapper = ResourceURI::getValue;

    public ResourceURISpec(String attrKey, Set<String> valuesRestrictedTo) {
        tagAttrSpec = TagAttrSpecImpl.<ResourceURI>builder()
                .attributeKey(attrKey)
                .valuesRestrictedTo(valuesRestrictedTo)
                .attrValueToObjectMapper(attrValueToObjectMapper)
                .tagAttrToObjectMapper(tagAttrToObjectMapper)
                .objToAttrValueMapper(objToAttrValueMapper)
                .build();

    }

    public static ResourceURISpec create(){
        return new ResourceURISpec(ResourceURI.class.getSimpleName(), Set.of());
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
    public Optional<ResourceURI> fromValue(String value) {
        return tagAttrSpec.fromValue(value);
    }

    @Override
    public Optional<ResourceURI> fromTagAttr(TagAttr tagAttr) {
        return tagAttrSpec.fromTagAttr(tagAttr);
    }

    @Override
    public TagAttr toTagAttr(ResourceURI object) {
        return tagAttrSpec.toTagAttr(object);
    }

    @Override
    public String toAttrValue(ResourceURI object) {
        return tagAttrSpec.toAttrValue(object);
    }

    @Override
    public Optional<ResourceURI> firstMatchFrom(Collection<TagAttr> tagAttrs) {
        return tagAttrSpec.firstMatchFrom(tagAttrs);
    }
}
