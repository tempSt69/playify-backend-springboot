package ad.com.playify.domain.port.out;

import java.io.File;
import java.io.InputStream;

import ad.com.playify.domain.entity.abstracts.StorageFile;

public interface StoragePort {
    public void saveObject(final String key, final File multipartFile);

    public InputStream getObjectInputStream(String fileName);

    public StorageFile getObjectInfo(String fileName);
}
