package zjh.learn.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import zjh.learn.Bean.AccountConfig;
import zjh.learn.Repository.TokenRepository;
import zjh.learn.Repository.UserRepository;
import zjh.learn.Service.TokenService;
import zjh.learn.bean.TokenDto;
import zjh.learn.bean.User;

/**
 * Created by jiahao on 16-8-10.
 */
@Service
@Qualifier("TokenService")
@EnableConfigurationProperties({AccountConfig.class})
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    private final AccountConfig accountConfig;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository,
                            UserRepository userRepository,
                            AccountConfig accountConfig) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.accountConfig = accountConfig;
    }

    @Override
    public TokenDto putToken(String userId, String ip) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return null;
        }
        tokenRepository.findByUserId(userId).forEach(token -> tokenRepository.delete(token.getId()));
        TokenDto token;
        do {
            token = new TokenDto(userId, ip);
        } while (tokenRepository.getByAuthCode(token.getAuthCode()) != null);
        long validPeriod = getValidPeriod();
        token.setExpireTime(token.getCreateTime() + validPeriod);
        token = tokenRepository.save(token);
        return token;
    }

    private long getValidPeriod() {
        String strVP = accountConfig != null ? accountConfig.getTokenValidPeriod() : null;
        long validPeriod;
        try {
            validPeriod = Long.valueOf(strVP);
        } catch (Exception e) {
            validPeriod = DEFAULT_VALID_PERIOD;
        }
        return validPeriod;
    }

    @Override
    public boolean removeTokenByAuthCode(String authCode) {
        TokenDto token = tokenRepository.getByAuthCode(authCode);
        tokenRepository.delete(token.getId());
        return true;
    }

    private final long DEFAULT_VALID_PERIOD = 1000 * 60 * 60 * 24;// token默认有效期为24小时
}
