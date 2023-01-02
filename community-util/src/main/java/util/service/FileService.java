package util.service;

import org.springframework.data.domain.Page;
import util.bean.File;

public interface FileService {
    void save(File file);

    Page<File> pageFile(Integer current, Integer size, String name);
}
