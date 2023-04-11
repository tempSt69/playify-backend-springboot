package ad.com.playify.domain.port.in;

import java.util.List;

import ad.com.playify.domain.entity.Song;

public interface SongServicePort {
    Song createSong(Song song);

    List<Song> getAllSongs();

    Song getSongById(String id);

    void streamSong(String id);
}
