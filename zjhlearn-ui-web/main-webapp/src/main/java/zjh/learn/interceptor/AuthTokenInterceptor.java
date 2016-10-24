package zjh.learn.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import zjh.learn.config.UrlsConfig;
import zjh.learn.utils.RedirectUrlUtils;
import zjh.learn.utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * authToken验证，用户未登陆需要先去登陆
 * 1.个人中心
 * Created by 11501 on 2016/10/24.
 */
@Component
public class AuthTokenInterceptor implements HandlerInterceptor{

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private UrlsConfig urlsConfig;

    @Autowired
    private RedirectUrlUtils redirectUrlUtils;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (userUtils.getCurrentUser() == null) {
            Device currentDevice = DeviceUtils.getCurrentDevice(httpServletRequest);
            if (currentDevice.isNormal()) {
                //登陆超时跳转到首页
                httpServletResponse.sendRedirect("/");
            } else {
                httpServletResponse.sendRedirect(String.format("%s?redirectUrl=%s",urlsConfig.getLoginUrl(),redirectUrlUtils.getRedirectUrl()));
            }
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
