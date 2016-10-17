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
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String registerUser(@RequestBody User user){
        if (user.getName() == null) {
            response.setStatus(400);
            return "name is not allowed to be null";
        }
        if (user.getPassword() == null) {
            response.setStatus(400);
            return "password is not allowed to be null";
        }
        String savedUser = accountService.saveUser(user);
        return savedUser;
    }
    //endregion

    //region 用户使用用户名密码登陆
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    @ApiOperation(value = "根据用户名和密码获取用户信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回对应用户信息"),
            @ApiResponse(code = 404, message = "用户信息不存在，用户名或者密码错误")
    })
    public User getUser(@ApiParam(value = "用户名", required = true) String name,
                        @ApiParam(value = "用户密码", required = true) String password) {
        if (password == null) {
            response.setStatus(404);
            return null;
        }
        User user = accountService.getUser(name, password);
        if (user == null) {
            response.setStatus(404);
            return null;
        }
        user.setPassword("");
        return user;
    }
    //endregion

}
