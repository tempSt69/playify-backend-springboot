package ad.com.playify.domain.port.in;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import ad.com.playify.domain.entity.Song;
import ad.com.playify.domain.entity.abstracts.StorageFile;

public interface SongServicePort {
    Song createSong(Song song, File file);

    List<Song> getAllSongs();

    Song getSongById(String id);

    InputStream getInputStream(String key);

    StorageFile getSongInfo(String key);
}
