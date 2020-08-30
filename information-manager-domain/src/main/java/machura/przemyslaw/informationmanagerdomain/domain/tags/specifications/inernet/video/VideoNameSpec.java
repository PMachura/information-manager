package machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet.video;

import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.DomainURI;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.video.VideoName;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttr;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpecImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet.DomainURISpec;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class VideoNameSpec implements TagAttrSpec<VideoName> {

    private final TagAttrSpecImpl<VideoName> tagAttrSpec;
    private final Function<String, VideoName> attrValueToObjectMapper = VideoName::new;
    private final Function<TagAttr, VideoName> tagAttrToObjectMapper = tagAttr -> new VideoName(tagAttr.getValue());
    private final Function<VideoName, String> objToAttrValueMapper = VideoName::getValue;

    public VideoNameSpec(String attrKey, Set<String> valuesRestrictedTo) {
        tagAttrSpec = TagAttrSpecImpl.<VideoName>builder()
                .attributeKey(attrKey)
                .valuesRestrictedTo(valuesRestrictedTo)
                .attrValueToObjectMapper(attrValueToObjectMapper)
                .tagAttrToObjectMapper(tagAttrToObjectMapper)
                .objToAttrValueMapper(objToAttrValueMapper)
                .build();

    }

    public static VideoNameSpec create(){
        return new VideoNameSpec(VideoName.class.getSimpleName(), Set.of());
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
    public Optional<VideoName> fromValue(String value) {
        return tagAttrSpec.fromValue(value);
    }

    @Override
    public Optional<VideoName> fromTagAttr(TagAttr tagAttr) {
        return tagAttrSpec.fromTagAttr(tagAttr);
    }

    @Override
    public TagAttr toTagAttr(VideoName object) {
        return tagAttrSpec.toTagAttr(object);
    }

    @Override
    public String toAttrValue(VideoName object) {
        return tagAttrSpec.toAttrValue(object);
    }

    @Override
    public Optional<VideoName> firstMatchFrom(Collection<TagAttr> tagAttrs) {
        return tagAttrSpec.firstMatchFrom(tagAttrs);
    }
}
