package zjh.learn.Service;

import org.springframework.stereotype.Service;
import zjh.learn.bean.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by jiahao on 16-8-9.
 */

@Service
public interface AccountService {

    //获取用户
    User getUser(String name,String phone, String password);

    //用户注册
    Map<String, Object> saveUser(User user);

    //用户登录
    Map<String, Object> login(User user, HttpServletRequest request);
}
