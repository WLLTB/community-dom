package blog.service;

import blog.bean.Blog;
import org.springframework.data.domain.Page;

public interface BlogService {
    Page<Blog> pageBlog(Integer size, Integer currentSize, String title);

    void saveBlog(Blog blog);

    void delete(String id);

    Blog findBlogById(String id);
}
