package machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet.video;

import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.video.ChannelName;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttr;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpecImpl;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class ChannelNameSpec implements TagAttrSpec<ChannelName> {

    private final TagAttrSpecImpl<ChannelName> tagAttrSpec;
    private final Function<String, ChannelName> attrValueToObjectMapper = ChannelName::new;
    private final Function<TagAttr, ChannelName> tagAttrToObjectMapper = tagAttr -> new ChannelName(tagAttr.getValue());
    private final Function<ChannelName, String> objToAttrValueMapper = ChannelName::getValue;

    public ChannelNameSpec(String attrKey, Set<String> valuesRestrictedTo) {
        tagAttrSpec = TagAttrSpecImpl.<ChannelName>builder()
                .attributeKey(attrKey)
                .valuesRestrictedTo(valuesRestrictedTo)
                .attrValueToObjectMapper(attrValueToObjectMapper)
                .tagAttrToObjectMapper(tagAttrToObjectMapper)
                .objToAttrValueMapper(objToAttrValueMapper)
                .build();

    }

    public static ChannelNameSpec create(){
        return new ChannelNameSpec(ChannelName.class.getSimpleName(), Set.of());
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
    public Optional<ChannelName> fromValue(String value) {
        return tagAttrSpec.fromValue(value);
    }

    @Override
    public Optional<ChannelName> fromTagAttr(TagAttr tagAttr) {
        return tagAttrSpec.fromTagAttr(tagAttr);
    }

    @Override
    public TagAttr toTagAttr(ChannelName object) {
        return tagAttrSpec.toTagAttr(object);
    }

    @Override
    public String toAttrValue(ChannelName object) {
        return tagAttrSpec.toAttrValue(object);
    }

    @Override
    public Optional<ChannelName> firstMatchFrom(Collection<TagAttr> tagAttrs) {
        return tagAttrSpec.firstMatchFrom(tagAttrs);
    }
}
