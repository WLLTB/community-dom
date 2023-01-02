package user.service;

import user.bean.User;

public interface UserService {
    String login(String username, String password);

    String register(User user);
}
