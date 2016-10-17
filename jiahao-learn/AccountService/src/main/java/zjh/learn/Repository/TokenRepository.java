package zjh.learn.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zjh.learn.bean.TokenDto;

import java.util.List;

/**
 * Created by jiahao on 16-8-10.
 */
public interface TokenRepository extends MongoRepository<TokenDto, String> {

    TokenDto getByAuthCode(String authCode);

    List<TokenDto> findByUserId(String userId);
}
