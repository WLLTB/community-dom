package user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import common.bean.User;
import org.springframework.stereotype.Service;
import user.repository.UserRepository;
import user.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserService {
}
