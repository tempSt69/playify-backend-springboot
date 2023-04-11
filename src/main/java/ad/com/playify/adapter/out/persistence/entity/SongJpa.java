package ad.com.playify.adapter.out.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Document("songs")
public class SongJpa {

    @Id
    private String id;
    private String name;
    private ArtistJpa artist;
    private String trackUrl;
    private Integer duration;
}
