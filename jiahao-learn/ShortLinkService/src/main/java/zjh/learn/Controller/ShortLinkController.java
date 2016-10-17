package zjh.learn.Controller;

import zjh.learn.Service.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiahao on 16-8-1.
 */
@RestController
@RequestMapping(value = "/api")
public class ShortLinkController {

    private ShortLinkService shortLinkService;

    @Autowired
    public ShortLinkController(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/shortlink")
    public String shortLink(@RequestBody String longUrl) {
        return shortLinkService.shortUrl(longUrl);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/shortlink/hashids")
    public String shortLinkByHashids(@RequestBody String longUrl) {
        return shortLinkService.shortUrlByHashids(longUrl);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/longlink")
    public String getlongLink(@RequestBody String shortUrl) {
        return shortLinkService.getLongLink(shortUrl);
    }
}
