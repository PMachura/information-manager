package machura.przemyslaw.informationmanagerdomain.domain.tags;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
import org.jsoup.nodes.Element;

import java.util.Set;


@ToString
public class TagImpl implements Tag {

    @NonNull
    private final String name;
    private final String text;
    private final Set<TagAttr> attributes;

    public static TagImpl from(Element element){
        return TagImpl.builder()
                .name(element.tagName())
                .attributes(TagAttrImpl.from(element.attributes()))
                .text(element.text())
                .build();
    }

    @Builder
    public TagImpl(@NonNull String name, String text, @Singular Set<TagAttr> attributes) {
        this.name = name;
        this.text = text;
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Set<TagAttr> getAttributes() {
        return attributes;
    }
}
