package zjh.learn.Service;

import org.springframework.stereotype.Service;
import zjh.learn.bean.User;

/**
 * Created by jiahao on 16-8-9.
 */

@Service
public interface AccountService {

    User getUser(String name, String password);

    String saveUser(User user);
}
