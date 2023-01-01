package util.controller;

import common.common.Result;
import common.utils.MinIOUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/util")
@RestController
public class UtilController {
    @PostMapping
    @ApiOperation("文件上传")
    public Result upload(@RequestParam(name = "file") MultipartFile file) {
        return Result.success(MinIOUtil.upload(file));
    }

    @GetMapping
    @ApiOperation("资源访问")
    public Result getFile(@RequestParam String name) {
        return Result.success(MinIOUtil.getUrl(name));
    }
}
