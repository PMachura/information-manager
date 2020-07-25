package machura.przemyslaw.informationmanagerdomain.domain.taggeddata;

import machura.przemyslaw.informationmanagerdomain.domain.tags.Tag;

import java.util.List;


public interface TaggedText {
    List<Tag> getTags();

    /**
     * Probably String type is not proper - temporary solution
     */
    String getText();
}
