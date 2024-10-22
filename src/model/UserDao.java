package model;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private Map<String, String> users;

    public UserDao() {
        users = new HashMap<>();
        users.put("admin", "password");  // Tên đăng nhập: admin, Mật khẩu: password
        users.put("user", "1234");        // Tên đăng nhập: user, Mật khẩu: 1234
    }

    public boolean checkLogin(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
