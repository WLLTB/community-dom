package blog.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import common.bean.Blog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogRepository extends BaseMapper<Blog> {
}
