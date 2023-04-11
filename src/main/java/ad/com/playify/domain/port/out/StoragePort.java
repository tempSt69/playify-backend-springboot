package ad.com.playify.domain.port.out;

import java.io.File;

public interface StoragePort {
    public void saveObject(final String key, final File multipartFile);

    public Object getObject(String fileName);
}
