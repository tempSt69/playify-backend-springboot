package ad.com.playify.adapter.out.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Document("artists")
public class ArtistJpa {

    @Id
    private String id;
    private String name;
    private String cover;
}
