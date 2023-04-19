package ad.com.playify.adapter.in.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import ad.com.playify.adapter.in.utils.StorageUtils;
import ad.com.playify.domain.entity.Song;
import ad.com.playify.domain.entity.abstracts.StorageFile;
import ad.com.playify.domain.port.in.SongServicePort;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongServicePort songServicePort;

    @GetMapping
    public List<Song> getSongs() {
        return songServicePort.getAllSongs();
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Song> createSong(@RequestBody Song song, @RequestPart MultipartFile file) {
        try {
            Song createdSong = songServicePort.createSong(song, StorageUtils.convertMultiPartToFile(file));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(createdSong);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(null);
        }
    }

    @GetMapping(value = "/{id}/stream", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> streamSong(@PathVariable String id) {
        Song song = songServicePort.getSongById(id);
        InputStream streamObject = songServicePort.getInputStream(song.getTrackUrl());

        final StreamingResponseBody stream = outputStream -> {
            try (streamObject) {
                int numberOfBytesToWrite = 0;
                byte[] data = new byte[1024];
                while ((numberOfBytesToWrite = streamObject.read(data, 0,
                        data.length)) != -1) {
                    outputStream.write(data, 0, numberOfBytesToWrite);
                }
            } catch (IOException e) {
                if (!e.getMessage().contains("Broken pipe")) {
                    // ResponseEntity.ok();
                }
            }
        };

        StorageFile storageObject = songServicePort.getSongInfo(song.getTrackUrl());

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-ranges", "bytes");
        headers.setContentType(MediaType.valueOf(storageObject.getContentType()));
        headers.setContentLength(storageObject.getContentLength());

        return ResponseEntity.ok().headers(headers).body(stream);

    }
}
