package user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import common.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository extends BaseMapper<User> {
}
