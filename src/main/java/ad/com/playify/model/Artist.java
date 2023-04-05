package ad.com.playify.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("artists")
public class Artist {
    
    @Id
    private String id;
    private String name;
    private String cover;

}
