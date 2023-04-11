package ad.com.playify.domain.port.in;

import java.util.List;

import ad.com.playify.domain.entity.Artist;

public interface ArtistServicePort {
    Artist createArtist(Artist artist);

    List<Artist> getAllArtists();
}
