package blog.controller;

import blog.service.BlogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import common.bean.Blog;
import common.common.Result;
import common.utils.MinIOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "博客")
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    @ApiOperation("博客分页")
    public Result searchBlog(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "5") Integer size, @RequestParam(required = false) String title) {
        LambdaQueryWrapper<Blog> blogLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<Blog> blogPage = new Page<>(current, size);
        blogLambdaQueryWrapper.like(!StringUtils.isEmpty(title), Blog::getTitle, title);
        Page<Blog> page = blogService.page(blogPage, blogLambdaQueryWrapper);
        return Result.success(page);
    }

    @PostMapping
    @ApiOperation("新增博客")
    public Result saveBlog(@RequestBody Blog blog) {
        boolean isSave = blogService.save(blog);
        return isSave ? Result.success(null) : Result.fail("删除失败!");
    }

    @PutMapping
    @ApiOperation("修改博客")
    public Result updateBlog(@RequestBody Blog blog) {
        boolean isUpdate = blogService.updateById(blog);
        return isUpdate ? Result.success(null) : Result.fail("删除失败!");
    }

    @DeleteMapping
    @ApiOperation("删除博客")
    public Result deleteBlog(@RequestParam String id) {
        boolean isRemove = blogService.removeById(id);
        return isRemove ? Result.success(null) : Result.fail("删除失败!");
    }
}
