package machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet.video;

import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.Builder;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.video.ChannelName;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.video.InternetVideo;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.video.VideoName;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.errors.FaultImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttr;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpec;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.DomainURI;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet.DomainURISpec;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.ResourceURI;
import machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.inernet.ResourceURISpec;
import org.springframework.util.StringUtils;

import java.util.Set;

public class InternetVideoSpec implements TagSpec<InternetVideo> {

    private final String tagName;
    private final ChannelNameSpec channelNameSpec;
    private final DomainURISpec domainUriSpec;
    private final VideoNameSpec videoNameSpec;
    private final ResourceURISpec resourceURISpec;
    private final Set<TagAttrSpec<?>> tagsAttributesSpecs;
    private final Set<TagAttrSpec<?>> requiredAttrsSpecs;

    @Builder(access = AccessLevel.PUBLIC)
    InternetVideoSpec(String tagName,
                      DomainURISpec domainUriSpec,
                      ResourceURISpec resourceURISpec,
                      ChannelNameSpec channelNameSpec,
                      VideoNameSpec videoNameSpec) {
        this.tagName = StringUtils.isEmpty(tagName) ? InternetVideo.class.getSimpleName() : tagName;
        this.domainUriSpec = domainUriSpec;
        this.resourceURISpec = resourceURISpec;
        this.channelNameSpec = channelNameSpec;
        this.videoNameSpec = videoNameSpec;
        this.tagsAttributesSpecs = Set.of(this.domainUriSpec, this.channelNameSpec, this.videoNameSpec, this.resourceURISpec);
        this.requiredAttrsSpecs = Set.of(this.domainUriSpec, this.channelNameSpec, this.videoNameSpec);
    }


    @Override
    public Tag toTag(InternetVideo obj) {
        return TagImpl.<InternetVideo>builder()
                .name(tagName)
                .attribute(channelNameSpec.toTagAttr(obj.getChannelName()))
                .attribute(domainUriSpec.toTagAttr(obj.getDomainUri()))
                .attribute(videoNameSpec.toTagAttr(obj.getVideoName()))
                .build();
    }

    @Override
    public Either<Fault, InternetVideo> toObj(Tag tag) {
        if (isCompatible(tag)) return Either.left(FaultImpl.fromReason("Incompatible tag"));

        Set<TagAttr> givenAttrs = tag.getAttributes();
        DomainURI domainUri = domainUriSpec.firstMatchFrom(givenAttrs).orElseGet(() -> new DomainURI(""));
        ResourceURI resourceURI = resourceURISpec.firstMatchFrom(givenAttrs).orElseGet(() -> new ResourceURI(""));
        ChannelName channelName = channelNameSpec.firstMatchFrom(givenAttrs).orElseGet(() -> new ChannelName(""));
        VideoName videoName = videoNameSpec.firstMatchFrom(givenAttrs).orElseGet(() -> new VideoName(""));

        return Either.right(
                InternetVideo.builder()
                        .domainURI(domainUri)
                        .resourceURI(resourceURI)
                        .channelName(channelName)
                        .videoName(videoName)
                        .build()
        );
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public Set<TagAttrSpec<?>> getSupportedAttr() {
        return tagsAttributesSpecs;
    }

    @Override
    public Set<TagAttrSpec<?>> getRequiredAttr() {
        return requiredAttrsSpecs;
    }

    @Override
    public boolean isTextRequired() {
        return false;
    }

}
