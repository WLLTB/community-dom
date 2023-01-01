package common.utils;

import cn.hutool.core.util.IdUtil;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MinIOUtil {
    private static final String url = "http://127.0.0.1:9000";
    private static final String accessKey = "minioadmin";
    private static final String secretKey = "minioadmin";

    private static final String bucket = "workspace";

    static MinioClient minioClient;

    public static String upload(MultipartFile file) {
        if (minioClient == null) {
            log.info("创建minioClient");
            minioClient = MinioClient
                    .builder()
                    .endpoint(url)
                    .credentials(accessKey, secretKey)
                    .build();

        }
        long fileSize = file.getSize();
        String fileName = IdUtil.simpleUUID();
        log.info("上传文件：文件名字：{}，文件大小：{}", fileName, fileSize);
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(fileName)
                    .stream(file.getInputStream(), fileSize, -1)
                    .contentType(file.getContentType())
                    .build());
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static String getUrl(String fileName) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs
                    .builder()
                    .bucket(bucket)
                    .object(fileName)
                    .method(Method.GET)
                    .expiry(1, TimeUnit.DAYS)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
