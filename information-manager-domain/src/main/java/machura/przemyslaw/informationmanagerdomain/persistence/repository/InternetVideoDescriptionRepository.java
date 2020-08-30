package machura.przemyslaw.informationmanagerdomain.persistence.repository;

import machura.przemyslaw.informationmanagerdomain.persistence.dao.InternetVideoDescriptionDAO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface InternetVideoDescriptionRepository extends ReactiveCrudRepository<InternetVideoDescriptionDAO, String> {
}
