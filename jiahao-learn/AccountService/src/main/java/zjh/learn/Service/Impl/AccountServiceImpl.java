package zjh.learn.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import zjh.learn.Common.EncryptProvider;
import zjh.learn.Repository.UserRepository;
import zjh.learn.Service.AccountService;
import zjh.learn.bean.User;

/**
 * Created by jiahao on 16-8-9.
 */
@Service
@Qualifier("AccountService")
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final EncryptProvider encryptProvider;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository,
                              EncryptProvider encryptProvider) {
        this.userRepository = userRepository;
        this.encryptProvider = encryptProvider;
    }

    @Override
    public User getUser(String name, String password) {
        password = encryptProvider.encryptPassword(password);
        return userRepository.findByNameAndPassword(name, password);
    }

    @Override
    public String saveUser(User user) {
        User existsUser = getUser(user.getName(), user.getPassword());
        if(existsUser != null) {
            return "user has exists, please login";
        }
        user.setPassword(encryptProvider.encryptPassword(user.getPassword()));
        return userRepository.save(user).toString();
    }


}
