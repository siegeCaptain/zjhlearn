package zjh.learn.Service;

import org.springframework.stereotype.Service;

/**
 * Created by jiahao on 16-8-1.
 */

@Service
public interface ShortLinkService {

    /**
     *生成短链接
     */
    String shortUrl(String longUrl);

    /**
     *生成短链接2
     */
    String shortUrlByHashids(String longUrl);

    /**
     *根据短链接还原长链接
     */
    String getLongLink(String shortUrl);
}
