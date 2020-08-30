package machura.przemyslaw.informationmanagerdomain.persistence.dao;

import lombok.Builder;
import lombok.Data;
import machura.przemyslaw.informationmanagerdomain.publishers.InternetVideoDescription;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "InternetVideoDescription")
@Builder
@Data
public class InternetVideoDescriptionDAO {
    @Id
    String id;

    private final InternetVideoDescription internetVideoDescription;

    public static InternetVideoDescriptionDAO from(InternetVideoDescription internetVideoDescription){
        return  InternetVideoDescriptionDAO.builder()
                .internetVideoDescription(internetVideoDescription)
                .build();
    }
}
