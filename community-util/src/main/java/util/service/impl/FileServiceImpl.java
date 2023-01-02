package util.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.bean.File;
import util.repository.FileRepository;
import util.service.FileService;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public void save(File file) {
        fileRepository.save(file);
    }

    @Override
    public Page<File> pageFile(Integer current, Integer size, String name) {
        Pageable pageable = PageRequest.of(current, size);
        if (StringUtils.isEmpty(name)) {
            return fileRepository.findAll(pageable);
        }
        return fileRepository.findFileByNameIsLike(name, pageable);
    }
}
