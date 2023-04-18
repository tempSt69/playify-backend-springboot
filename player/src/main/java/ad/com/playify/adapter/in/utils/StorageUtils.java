package ad.com.playify.adapter.in.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class StorageUtils {
    public static File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }
}
