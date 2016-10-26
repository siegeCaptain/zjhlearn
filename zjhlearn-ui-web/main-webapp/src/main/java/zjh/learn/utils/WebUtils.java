package zjh.learn.utils;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

    /**
     * 添加cookie
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @param name
     *            Cookie名称
     * @param value
     *            Cookie值
     * @param domain
     *            域
     * @param maxAge
     *            有效期
     * @param path
     *            path
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path, String domain) {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        Assert.hasText(value);

        try {
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
            Cookie cookie = new Cookie(name, value);
            if (maxAge != null) {
                cookie.setMaxAge(maxAge);
            }
            if (!StringUtils.isEmpty(path)) {
                cookie.setPath(path);
            }
            if (!StringUtils.isEmpty(domain)) {
                cookie.setDomain(domain);
            }
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 获取cookie
     *
     * @param request
     *            HttpServletRequest
     * @param name
     *            Cookie名称
     * @return Cookie值，若不存在则返回null
     */
    public static String getCookie(HttpServletRequest request, String name) {
        Assert.notNull(request);
        Assert.hasText(name);

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            try {
                name = URLEncoder.encode(name, "UTF-8");
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    }
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 移除cookie
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @param name
     *            Cookie名称
     * @param path
     *            路径
     * @param domain
     *            域
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain) {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);

        try {
            name = URLEncoder.encode(name, "UTF-8");
            Cookie cookie = new Cookie(name, null);
            cookie.setMaxAge(0);
            if (!StringUtils.isEmpty(path)) {
                cookie.setPath(path);
            }
            if (!StringUtils.isEmpty(domain)) {
                cookie.setDomain(domain);
            }
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
