package zjh.learn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import zjh.learn.Service.AccountService;
import zjh.learn.bean.User;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by jiahao on 16-8-9.
 */

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    private final HttpServletResponse response;
    private final AccountService accountService;

    @Autowired
    public AccountController(HttpServletResponse response,
                             AccountService accountService) {
        this.response = response;
        this.accountService = accountService;
    }

    //region 用户注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@RequestBody User user){
        String savedUser = accountService.saveUser(user);
        return savedUser;
    }
    //endregion

    //region 用户使用手机号密码登陆
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public User getUser(@RequestBody User user) {

        User existUser = accountService.getUser(user.getName(), user.getPhone(), user.getPassword());
        if (existUser == null) {
            return null;
        }
        existUser.setPassword("");
        return existUser;
    }
    //endregion

}
