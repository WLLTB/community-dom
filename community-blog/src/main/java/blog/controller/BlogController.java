package blog.controller;

import blog.bean.Blog;
import blog.service.BlogService;
import common.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "博客")
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    @ApiOperation("博客分页")
    public Result searchBlog(@RequestParam(defaultValue = "1") Integer current, @RequestParam(defaultValue = "5") Integer size, @RequestParam(required = false) String title) {
        return Result.success(blogService.pageBlog(current, size, title));
    }

    @PostMapping
    @ApiOperation("新增博客")
    public Result saveBlog(@RequestBody Blog blog) {
        blogService.saveBlog(blog);
        return Result.success(null);
    }

    @PutMapping
    @ApiOperation("修改博客")
    public Result updateBlog(@RequestBody Blog blog) {
        blogService.saveBlog(blog);
        return Result.success(null);
    }

    @DeleteMapping
    @ApiOperation("删除博客")
    public Result deleteBlog(@RequestParam String id) {
        blogService.delete(id);
        return Result.success(null);
    }

    @GetMapping("/getById")
    @ApiOperation("获取详情")
    public Result getBlogById(@RequestParam String id) {
        Blog blog = blogService.findBlogById(id);
        return blog != null ? Result.success(blog) : Result.fail("博客不存在");
    }

//
//    @GetMapping("/type")
//    @ApiOperation("获取分类")
//    public Result typeList() {
//
//    }
}
