package ad.com.playify.adapter.out.storage.adapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;

import ad.com.playify.domain.port.out.StoragePort;

public class StorageAdapter implements StoragePort {
    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucket.name}")
    private String s3BucketName;

    @Async
    @Override
    public S3Object getObject(String fileName) {
        return amazonS3.getObject(s3BucketName, fileName);
    }

    @Async
    @Override
    public void saveObject(final String key, final File file) {
        try {
            amazonS3.putObject(s3BucketName, key, file);
            Files.delete(file.toPath());
        } catch (AmazonServiceException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
