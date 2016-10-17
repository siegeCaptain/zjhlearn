package zjh.learn.Repository;

import zjh.learn.Bean.ResUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiahao on 16-8-1.
 */

@Repository
public interface ShortLinkRepository extends MongoRepository<ResUrl, String>{

    /**
     * 通过短链接查询原链接
     */
    ResUrl findByShortUrl(String ShortUrl);

    /**
     *通过长链接查询ResUrl对象
     */
    ResUrl findByLongUrl(String LongUrl);

    ResUrl findById(String Id);
}

