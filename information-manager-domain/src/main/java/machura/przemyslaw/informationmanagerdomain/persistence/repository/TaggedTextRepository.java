package machura.przemyslaw.informationmanagerdomain.persistence.repository;

import machura.przemyslaw.informationmanagerdomain.persistence.dao.tags.TaggedTextDAO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TaggedTextRepository extends ReactiveCrudRepository<TaggedTextDAO, String> {
}
