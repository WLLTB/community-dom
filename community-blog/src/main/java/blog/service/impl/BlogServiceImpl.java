package blog.service.impl;

import blog.bean.Blog;
import blog.repository.BlogRepository;
import blog.service.BlogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogRepository blogRepository;

    @Override
    public Page<Blog> pageBlog(Integer current, Integer size, String title) {
        Pageable pageRequest = PageRequest.of(current, size);
        if (StringUtils.isEmpty(title)) {
            return blogRepository.findAll(pageRequest);
        }
        return blogRepository.findBlogByTitleIsLike(title, pageRequest);
    }

    @Override
    public void saveBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        blogRepository.save(blog);
    }

    @Override
    public void delete(String id) {
        blogRepository.deleteById(id);
    }
}
