package user.service.impl;

import user.bean.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.repository.UserRepository;
import user.service.UserService;
import user.utils.RedisUtil;
import user.utils.UserUtils;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String login(String username, String password) {
        password = UserUtils.MD5DigestHex(password);
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if (user == null) {
            return StringUtils.EMPTY;
        }

        String token = UserUtils.createToken(user);
        redisUtil.set(username, token);

        return token;
    }

    @Override
    public String register(User user) {
        String id = user.getId();
        if (!StringUtils.isEmpty(id)) {
            User existUser = userRepository.findUserById(id);
            if (existUser != null) {
                return "注册失败，账号已经存在";
            }
        }

        user.setPassword(UserUtils.MD5DigestHex(user.getPassword()));
        user.setUpdateTime(new Date());
        userRepository.save(user);

        return StringUtils.EMPTY;
    }
}
