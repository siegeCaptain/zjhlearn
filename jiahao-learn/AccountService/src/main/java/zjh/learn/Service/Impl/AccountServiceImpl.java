package zjh.learn.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import zjh.learn.Common.EncryptProvider;
import zjh.learn.Repository.UserRepository;
import zjh.learn.Service.AccountService;
import zjh.learn.Service.TokenService;
import zjh.learn.Utils.HttpAccessHelper;
import zjh.learn.bean.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiahao on 16-8-9.
 */
@Service
@Qualifier("AccountService")
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final EncryptProvider encryptProvider;
    private final TokenService tokenService;
    private final HttpAccessHelper httpAccessHelper;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository,
                              EncryptProvider encryptProvider,
                              TokenService tokenService,
                              HttpAccessHelper httpAccessHelper) {
        this.userRepository = userRepository;
        this.encryptProvider = encryptProvider;
        this.tokenService = tokenService;
        this.httpAccessHelper = httpAccessHelper;
    }

    //根据账号或手机号和密码获取用户
    @Override
    public User getUser(String name, String phone, String password) {
        password = encryptProvider.encryptPassword(password);
        User targetUser = userRepository.findByNameAndPassword(name, password);
        if (targetUser == null || StringUtils.isEmpty(name)) {
            targetUser = userRepository.findByPhoneAndPassword(phone, password);
        }
        return targetUser;
    }

    //用户注册
    @Override
    public Map<String, Object> saveUser(User user) {
        Map<String, Object> data = new HashMap<String, Object>();
        User existsUser = getUser(user.getName(), user.getPhone(), user.getPassword());
        if(existsUser != null) {
            data.put("code", 0);
            data.put("message", "用户已存在,请登录!");
            return data;
        }
        user.setPassword(encryptProvider.encryptPassword(user.getPassword()));
        user.setCreated_time((new Date()).toString());
        User saved = userRepository.save(user);
        saved.setPassword("");
        data.put("code", 1);
        data.put("message", "注册成功!");
        data.put("savedUser", saved);
        return data;
    }

    //用户登录
    @Override
    public Map<String, Object> login(User user, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<String, Object>();
        String password = encryptProvider.encryptPassword(user.getPassword());

        User targetUser = userRepository.findByName(user.getName());
        if (targetUser == null || user.getName() == null) {
            targetUser = userRepository.findByPhone(user.getPhone());
            if (targetUser == null || user.getPhone() == null) {
                data.put("code", 0);
                data.put("message", "用户不存在,请注册!");
                return data;
            }
            return validatePsw(targetUser, password, request);
        }
        return validatePsw(targetUser, password, request);
    }

    //验证密码
    public Map<String, Object> validatePsw(User targetUser, String password, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<String, Object>();
        if (targetUser.getPassword().equals(password)) {
            data.put("code", 1);
            data.put("message", "登陆成功!");
            data.put("loginUser", targetUser);
            tokenService.putToken(targetUser.getUserId(), httpAccessHelper.getUserIp(request));
            return data;
        } else {
            data.put("code", 2);
            data.put("message", "密码错误!");
            return data;
        }
    }


}
