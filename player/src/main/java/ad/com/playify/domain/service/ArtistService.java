package ad.com.playify.domain.service;

import java.util.List;

import ad.com.playify.domain.entity.Artist;
import ad.com.playify.domain.port.in.ArtistServicePort;
import ad.com.playify.domain.port.out.ArtistPersistencePort;

public class ArtistService implements ArtistServicePort {

    private ArtistPersistencePort artistPersistencePort;

    public ArtistService(ArtistPersistencePort artistPersistencePort) {
        this.artistPersistencePort = artistPersistencePort;
    }

    @Override
    public Artist createArtist(Artist artist) {
        return artistPersistencePort.createArtist(artist);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistPersistencePort.getAllArtists();
    }

}
