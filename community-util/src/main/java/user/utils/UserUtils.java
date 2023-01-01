package user.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import common.bean.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class UserUtils {

    public static final String USER_ID = "USER_ID";
    public static final String USER_NAME = "USER_NAME";
    public static final String NICK_NAME = "NICK_NAME";
    public static final String EMAIL = "EMAIL";
    private static final String SALT = "WLLTB";

    public static String MD5DigestHex(String password) {
        return new Digester(DigestAlgorithm.MD5).digestHex(password);
    }

    public static String createToken(User user) {
        Map<String, Object> data = new HashMap<>();
        data.put(USER_ID, user.getId());
        data.put(USER_NAME, user.getUsername());
        data.put(NICK_NAME, user.getNickname());
        data.put(EMAIL, user.getEmail());
        return JWTUtil.createToken(data, SALT.getBytes());
    }

    public static Object parseToken(String token, String key) {
        final JWT jwt = JWTUtil.parseToken(token);

        jwt.getHeader(JWTHeader.TYPE);
        return jwt.getPayload(key);
    }

    public static boolean verifyToken(String token) {
        return JWTUtil.verify(token, SALT.getBytes());
    }
}
