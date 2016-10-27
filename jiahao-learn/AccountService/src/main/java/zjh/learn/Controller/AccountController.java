package zjh.learn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zjh.learn.Service.AccountService;
import zjh.learn.Service.TokenService;
import zjh.learn.bean.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by jiahao on 16-8-9.
 */

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    private final AccountService accountService;
    private final TokenService tokenService;

    @Autowired
    public AccountController(AccountService accountService,
                             TokenService tokenService) {
        this.accountService = accountService;
        this.tokenService = tokenService;
    }

    //region 用户注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, Object> registerUser(@RequestBody User user, HttpServletRequest request){
        return accountService.saveUser(user, request);
    }
    //endregion

    //region 用户使用手机号密码登陆
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> getUser(@RequestBody User user, HttpServletRequest request) {
        return accountService.login(user, request);
    }
    //endregion

    //region 用户退出登陆
    @RequestMapping(value = "/logout/{authToken}", method = RequestMethod.GET)
    public boolean getUser(@PathVariable("authToken") String authToken) {
        return tokenService.removeTokenByAuthCode(authToken);
    }
    //endregion

}
