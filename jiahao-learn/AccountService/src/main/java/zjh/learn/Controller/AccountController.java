package zjh.learn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import zjh.learn.Service.AccountService;
import zjh.learn.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Object> registerUser(@RequestBody User user){
        return accountService.saveUser(user);
    }
    //endregion

    //region 用户使用手机号密码登陆
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> getUser(@RequestBody User user, HttpServletRequest request) {
        return accountService.login(user, request);
    }
    //endregion

}
