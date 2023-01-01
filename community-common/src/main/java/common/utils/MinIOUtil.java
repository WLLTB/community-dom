package common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class MinIOUtil {
    private static final String url = "http://127.0.0.1:9000";
    private static final String accessKey = "minioadmin";
    private static final String secretKey = "minioadmin";
    static MinioClient minioClient = MinioClient.builder()
            .endpoint(url)
            .credentials(accessKey, secretKey)
            .build();

    public boolean upload(MultipartFile file) {
        String bucket = "workspace";
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
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
        return true;
    }
}
