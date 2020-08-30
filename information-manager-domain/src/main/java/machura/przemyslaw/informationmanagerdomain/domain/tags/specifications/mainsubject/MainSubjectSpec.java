package machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.mainsubject;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.internet.video.InternetVideo;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.errors.FaultImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpec;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.MainSubject;
import org.springframework.util.StringUtils;

import java.util.Set;


public class MainSubjectSpec implements TagSpec<MainSubject> {

    private final String tagName;
    private final Set<TagAttrSpec<?>> supportedAttrs = Set.of();
    private final Set<TagAttrSpec<?>> requiredAttrs = Set.of();

    public MainSubjectSpec(String tagName) {
        this.tagName = StringUtils.isEmpty(tagName) ? MainSubject.class.getSimpleName() : tagName;
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public Set<TagAttrSpec<?>> getSupportedAttr() {
        return supportedAttrs;
    }

    @Override
    public Set<TagAttrSpec<?>> getRequiredAttr() {
        return requiredAttrs;
    }

    @Override
    public boolean isTextRequired() {
        return true;
    }

    @Override
    public Tag toTag(MainSubject obj) {
        return TagImpl.<MainSubject>builder()
                .name(getTagName())
                .build();
    }

    @Override
    public Either<Fault, MainSubject> toObj(Tag tag) {
        return isCompatible(tag) ?
                Either.right(new MainSubject(tag.getText())) : Either.left(FaultImpl.fromReason("Incompatible tag"));
    }
}
