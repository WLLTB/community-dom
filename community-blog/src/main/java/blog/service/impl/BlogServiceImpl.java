package blog.service.impl;

import blog.repository.BlogRepository;
import blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import common.bean.Blog;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogRepository, Blog> implements BlogService {
}
