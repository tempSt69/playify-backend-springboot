package ad.com.playify.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import ad.com.playify.bucket.AWS.S3Client;
import ad.com.playify.model.Song;
import ad.com.playify.service.SongService;

@RestController
@RequestMapping("/song")
@CrossOrigin
public class SongController {

    @Autowired
    private SongService songService;
    @Autowired
    private S3Client s3Client;

    @GetMapping()
    public List<Song> getSong() {
        return songService.getSongs();
    }

    @GetMapping(value = "/{id}/stream", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> getSongStream(@PathVariable String id) {
        Song song = songService.getSong(id);
        S3Object s3object = s3Client.getObject(song.getTrackUrl());

        S3ObjectInputStream streamObject = s3object.getObjectContent();

        final StreamingResponseBody stream = outputStream -> {
            try (streamObject) {
                int numberOfBytesToWrite = 0;
                byte[] data = new byte[1024];
                while ((numberOfBytesToWrite = streamObject.read(data, 0, data.length)) != -1) {
                    outputStream.write(data, 0, numberOfBytesToWrite);
                }
            } catch (IOException e) {
                if (!e.getMessage().contains("Broken pipe")) {
                    // ResponseEntity.ok();
                }
            }
        };

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-ranges", "bytes");
        headers.setContentType(MediaType.valueOf(s3object.getObjectMetadata().getContentType()));
        headers.setContentLength(s3object.getObjectMetadata().getContentLength());

        return ResponseEntity.ok().headers(headers).body(stream);
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Song> postSong(
            @ModelAttribute Song song,
            @RequestParam("file") MultipartFile file) {

        String key = UUID.randomUUID().toString();
        s3Client.save(UUID.randomUUID().toString(), file);

        song.setTrackUrl(key);

        Song result = songService.createSong(song);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON).body(result);
    }

}
