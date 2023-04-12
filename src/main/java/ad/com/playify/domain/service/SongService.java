package ad.com.playify.domain.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import ad.com.playify.domain.entity.Song;
import ad.com.playify.domain.entity.abstracts.StorageFile;
import ad.com.playify.domain.port.in.SongServicePort;
import ad.com.playify.domain.port.out.SongPersistencePort;
import ad.com.playify.domain.port.out.StoragePort;

public class SongService implements SongServicePort {

    private SongPersistencePort songPersistencePort;
    private StoragePort storagePort;

    public SongService(SongPersistencePort songPersistencePort, StoragePort storagePort) {
        this.songPersistencePort = songPersistencePort;
        this.storagePort = storagePort;
    }

    @Override
    public Song createSong(Song song, File file) {
        String uuid = UUID.randomUUID().toString();
        storagePort.saveObject(uuid, file);
        song.setTrackUrl(uuid);
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
    public InputStream getInputStream(String key) {
        return storagePort.getObjectInputStream(key);
    }

    @Override
    public StorageFile getSongInfo(String key) {
        return storagePort.getObjectInfo(key);
    }
}
