package user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import common.bean.User;
import common.common.Result;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.service.UserService;
import user.utils.RedisUtil;
import user.utils.UserUtils;

@Api("用户")
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(username)) {
            return Result.fail("账号或密码为空");
        }
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, username);

        User dbUser = userService.getOne(userLambdaQueryWrapper);
        if (dbUser == null) {
            return Result.fail("账号不存在");
        }
        String dbPassword = dbUser.getPassword();
        password = UserUtils.MD5DigestHex(password);
        String token = UserUtils.createToken(dbUser);
        redisUtil.set(username, token);
        return dbPassword.equals(password) ? Result.success(token) : Result.fail("账号密码不匹配");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(user.getNickname()) || StringUtils.isEmpty(user.getEmail())) {
            return Result.fail("用户名、昵称或密码为空");
        }
        password = UserUtils.MD5DigestHex(password);
        user.setPassword(password);
        boolean isSave = userService.save(user);
        return isSave ? Result.success(null) : Result.fail("注册失败");
    }
}
