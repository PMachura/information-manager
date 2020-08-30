package machura.przemyslaw.informationmanagerdomain.domain.tags;

import lombok.*;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;



@Builder(builderClassName = "Builder")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@ToString
public class TagAttrImpl implements TagAttr {
    private final String key;
    private final String value;

    public static TagAttrImpl from(Attribute attribute){
        return new TagAttrImpl(attribute.getKey(), attribute.getValue());
    }

    public static List<TagAttrImpl> from(Attributes attributes){
        return from(attributes.asList());
    }

    public static List<TagAttrImpl> from(Collection<? extends Attribute> attributes){
        return attributes.stream()
                .map(TagAttrImpl::from)
                .collect(Collectors.toList());
    }
}
