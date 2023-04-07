package ad.com.playify.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("songs")
public class Song {

    @Id
    private String id;
    private String name;
    private Integer duration;
    private String trackUrl;

    private Artist artist;

    public Song(final String id, final String name, final Artist artist, final Integer duration,
            final String trackUrl) {
        super();
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.trackUrl = trackUrl;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTrackUrl() {
        return this.trackUrl;
    }

    public void setTrackUrl(String trackUrl) {
        this.trackUrl = trackUrl;
    }

}
