package ad.com.playify.domain.service;

import java.util.List;

import ad.com.playify.domain.entity.Song;
import ad.com.playify.domain.port.in.SongServicePort;
import ad.com.playify.domain.port.out.SongPersistencePort;

public class SongService implements SongServicePort {

    private SongPersistencePort songPersistencePort;

    public SongService(SongPersistencePort songPersistencePort) {
        this.songPersistencePort = songPersistencePort;
    }

    @Override
    public Song createSong(Song song) {
        return songPersistencePort.createSong(song);
    }

    @Override
    public List<Song> getAllSongs() {
        return songPersistencePort.getAllSongs();
    }

    @Override
    public Song getSongById(String id) {
        return songPersistencePort.getSongById(id);
    }

    @Override
    public void streamSong(String id) {
        // TODO
    }

}
