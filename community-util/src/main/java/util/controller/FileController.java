package util.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.bean.File;

@RequestMapping("/file")
@RestController
public class FileController {
    @PostMapping
    public void yumUpload(@RequestBody File file) {

    }
}
