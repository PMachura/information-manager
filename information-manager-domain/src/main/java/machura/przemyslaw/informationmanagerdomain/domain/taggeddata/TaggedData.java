package machura.przemyslaw.informationmanagerdomain.domain.taggeddata;

import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;

import java.util.List;


public interface TaggedData<T> {
    List<Tag<?>> getTags();
    T getData();
}
