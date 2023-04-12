package ad.com.playify.domain.entity.abstracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageFile {
    private String contentType;
    private Long contentLength;

    public StorageFile(String contentType, Long contentLength) {
        this.contentType = contentType;
        this.contentLength = contentLength;
    }
}
