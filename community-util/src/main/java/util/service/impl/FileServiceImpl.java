package util.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
}
