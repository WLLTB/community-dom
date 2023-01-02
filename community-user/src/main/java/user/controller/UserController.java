package user.controller;

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

        String token = userService.login(username, password);
        if (StringUtils.isEmpty(token)) {
            Result.fail("账号密码不匹配！");
        }
        return Result.success(token);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(user.getNickname()) || StringUtils.isEmpty(user.getEmail())) {
            return Result.fail("用户名、昵称或密码为空");
        }
        String result = userService.register(user);
        if (StringUtils.isEmpty(result)){
            return Result.success(null);
        }
        return Result.fail(result);
    }
}
