package zjh.learn.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by jiahao.zhang on 2016/10/24.
 */
public class WebUtils {

    public static final String AUTH_TOKEN_COOKIE_NAME = "authToken";

    //获取登陆用户authToken
    public static String getAuthToken(HttpServletRequest request) {
        String authToken = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equalsIgnoreCase(AUTH_TOKEN_COOKIE_NAME)) {
                    authToken = cookie.getValue().toString();
                    break;
                }
            }
        }
        return authToken ;

    }

    //获取用户真实的IP
    public static String getUserIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getEncodeFullUrl(HttpServletRequest request) throws UnsupportedEncodingException {
        return URLEncoder.encode(getFullUrl(request),"utf-8");
    }

    public static String getFullUrl(HttpServletRequest request) throws UnsupportedEncodingException {
        return request.getRequestURL() + (StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString());
    }
}
