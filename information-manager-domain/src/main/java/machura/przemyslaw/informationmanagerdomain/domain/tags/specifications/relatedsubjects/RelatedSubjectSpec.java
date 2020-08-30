package machura.przemyslaw.informationmanagerdomain.domain.tags.specifications.relatedsubjects;

import io.vavr.control.Either;
import machura.przemyslaw.informationmanagerdomain.domain.datadescriptors.RelatedSubject;
import machura.przemyslaw.informationmanagerdomain.domain.errors.Fault;
import machura.przemyslaw.informationmanagerdomain.domain.errors.FaultImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagAttrSpec;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagImpl;
import machura.przemyslaw.informationmanagerdomain.domain.tags.TagSpec;
import org.springframework.util.StringUtils;

import java.util.Set;

public class RelatedSubjectSpec implements TagSpec<RelatedSubject> {

    private final String tagName;
    private final Set<TagAttrSpec<?>> supportedAttrs = Set.of();
    private final Set<TagAttrSpec<?>> requiredAttrs = Set.of();

    public RelatedSubjectSpec(String tagName) {
        this.tagName = StringUtils.isEmpty(tagName) ? RelatedSubject.class.getSimpleName() : tagName;
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
    public Tag toTag(RelatedSubject obj) {
        return TagImpl.<RelatedSubject>builder()
                .name(getTagName())
                .build();
    }

    @Override
    public Either<Fault, RelatedSubject> toObj(Tag tag) {
        return isCompatible(tag) ?
                Either.right(new RelatedSubject(tag.getText())) : Either.left(FaultImpl.fromReason("Incompatible tag"));
    }
}
