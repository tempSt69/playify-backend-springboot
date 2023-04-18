package ad.com.playify.domain.port.out;

import java.util.List;

import ad.com.playify.domain.entity.Artist;

public interface ArtistPersistencePort {
    Artist createArtist(Artist artist);

    List<Artist> getAllArtists();
}
