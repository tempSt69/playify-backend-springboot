package ad.com.playify.domain.port.in;

import java.io.File;
import java.util.List;

import ad.com.playify.domain.entity.Song;

public interface SongServicePort {
    Song createSong(Song song, File file);

    List<Song> getAllSongs();

    Song getSongById(String id);
}
