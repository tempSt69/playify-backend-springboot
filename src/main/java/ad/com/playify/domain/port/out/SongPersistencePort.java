package ad.com.playify.domain.port.out;

import java.util.List;

import ad.com.playify.domain.entity.Song;

public interface SongPersistencePort {
    Song createSong(Song song);

    List<Song> getAllSongs();

    Song getSongById(String id);
}
