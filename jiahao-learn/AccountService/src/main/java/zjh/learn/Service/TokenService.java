package zjh.learn.Service;

import org.springframework.stereotype.Service;
import zjh.learn.bean.TokenDto;

/**
 * Created by jiahao on 16-8-10.
 */
@Service
public interface TokenService {

    TokenDto putToken(String userId, String ip);

    boolean removeTokenByAuthCode(String authCode);
}
