package machura.przemyslaw.informationmanagerdomain.domain.tags.specified.datasourcetag.videoportal;

import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.Builder;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.errors.FaultImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagDefault;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.attributes.TagAttributeSpec;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VideoPortalDataSourceSpec implements TagSpec<VideoPortal> {

    static final String TAG_NAME = "VideoPortalDatasource";
    private final ChannelNameSpec channelNameSpec;
    private final DomainUriSpec domainUriSpec;
    private final MovieNameSpec movieNameSpec;
    private final Set<TagAttributeSpec<?>> tagsAttributesSpecs;

    @Builder(access = AccessLevel.PACKAGE)
    VideoPortalDataSourceSpec(DomainUriSpec domainUriSpec, ChannelNameSpec channelNameSpec, MovieNameSpec movieNameSpec) {
        this.channelNameSpec = channelNameSpec;
        this.domainUriSpec = domainUriSpec;
        this.movieNameSpec = movieNameSpec;
        tagsAttributesSpecs = Set.of(this.domainUriSpec, this.channelNameSpec, this.movieNameSpec);
    }

    @Override
    public Tag<VideoPortal> map(VideoPortal obj) {
        return TagDefault.<VideoPortal>builder()
                .name(TAG_NAME)
                .attribute(channelNameSpec.map(obj.getChannelName()))
                .attribute(domainUriSpec.map(obj.getDomainUri()))
                .attribute(movieNameSpec.map(obj.getMovieName()))
                .build();
    }

    @Override
    public String getTagName() {
        return TAG_NAME;
    }


    @Override
    public Set<String> getMainValuesRestrictedTo() {
        return Set.of();
    }

    @Override
    public Set<TagAttributeSpec<?>> getAttributesRestrictedTo() {
        return tagsAttributesSpecs;
    }

    @Override
    public Set<TagAttributeSpec<?>> getRequiredAttributes() {
        return tagsAttributesSpecs;
    }

    public TagBuilder createTagBuilder() {
        return new TagBuilder();
    }

    //PMa todo probable for removal
    public class TagBuilder {
        private String channelName = "";
        private String domainUri = "";
        private String movieName = "";

        private TagBuilder() {
        }

        public TagBuilder domainURI(String domainURI) {
            this.domainUri = domainURI;
            return this;
        }

        public TagBuilder channelName(String channelName) {
            this.channelName = channelName;
            return this;
        }

        public TagBuilder movieName(String movieName) {
            this.movieName = movieName;
            return this;
        }

        public Either<Fault, VideoPortal> build() {
            Either<Fault, DomainUri> domainUriMaybe = domainUriSpec.create(domainUri);
            Either<Fault, ChannelName> channelNameMaybe = channelNameSpec.create(channelName);
            Either<Fault, MovieName> movieNameMaybe = movieNameSpec.create(movieName);
            return Stream.of(domainUriMaybe, channelNameMaybe, movieNameMaybe).allMatch(Either::isRight) ?
                    Either.right(VideoPortal.builder()
                            .domainURI(domainUri)
                            .channelName(channelName)
                            .movieName(movieName)
                            .build())
                    : Either.left(FaultImpl.fromFaults(Stream.of(domainUriMaybe, channelNameMaybe, movieNameMaybe)
                    .filter(Either::isLeft)
                    .map(Either::getLeft)
                    .collect(Collectors.toList())));
        }
    }

}
