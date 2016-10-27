package zjh.learn.uiController;

import freemarker.ext.beans.HashAdapter;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import zjh.learn.bean.User;
import zjh.learn.config.AccountConstantConfig;
import zjh.learn.config.UrlsConfig;
import zjh.learn.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 11501 on 2016/10/26.
 */
@Controller
public class LoginController {

    @Autowired
    private AccountConstantConfig accountConstantConfig;
    @Autowired
    private UrlsConfig urlsConfig;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${authToken.expire}")
    private Integer maxAge;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> data = restTemplate.postForObject(urlsConfig.getLoginUrl(), user, Map.class);
        if (data.get("authToken") != null) {
            WebUtils.addCookie(request, response, "authToken", data.get("authToken").toString(), maxAge, "/", null);
        }
        return data;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        String authToken = WebUtils.getAuthToken(request);
        boolean result = restTemplate.getForObject(String.format("%s/api/account/logout/%s", urlsConfig.getAcountServiceApiUrl(), authToken),
                Boolean.class);
        if (authToken != null && result) {
            WebUtils.removeCookie(request, response, "authToken", "/", null);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> data = restTemplate.postForObject(String.format("%s/api/account/register", urlsConfig.getAcountServiceApiUrl()),
                user, Map.class);
        if (data.get("authToken") != null) {
            WebUtils.addCookie(request, response, "authToken", data.get("authToken").toString(), maxAge, "/", null);
        }
        return data;
    }
}
