package ad.com.playify.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Song {
    private String id;
    private String name;
    private Artist artist;
    private String trackUrl;
    private Integer duration;
}
