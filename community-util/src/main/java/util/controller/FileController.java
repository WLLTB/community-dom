package util.controller;

import common.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.bean.File;
import util.service.FileService;

@RequestMapping("/file")
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    public Result yumUpload(@RequestBody File file) {
        fileService.save(file);
        return Result.success(null);
    }

    @GetMapping
    public Result pageFile(Integer current, Integer size, String name) {
        return Result.success(fileService.pageFile(current, size, name));
    }
}
