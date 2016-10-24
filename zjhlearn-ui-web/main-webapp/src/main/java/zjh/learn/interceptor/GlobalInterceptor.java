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
import zjh.learn.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局
 * 设置当前用户
 * 设置当前url
 *
 * Created by jiahao.zhang on 2016/10/24.
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor{

    @Autowired
    private UserUtils userUtils;
    @Autowired
    private RedirectUrlUtils redirectUrlUtils;
    @Autowired
    private UrlsConfig urlsConfig;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        userUtils.setCurrentUser(httpServletRequest);
        httpServletRequest.setAttribute("acountServiceApiUrl", urlsConfig.getAcountServiceApiUrl());
        httpServletRequest.setAttribute("isLogin", userUtils.isLogin());
        httpServletRequest.setAttribute("isLoginStr", userUtils.isLogin() + "");
        httpServletRequest.setAttribute("indexUrl", urlsConfig.getIndexUrl());
        httpServletRequest.setAttribute("payUrl", urlsConfig.getPayUrl());
        httpServletRequest.setAttribute("payWechatUrl", urlsConfig.getPayWechatUrl());

        Device currentDevice = DeviceUtils.getCurrentDevice(httpServletRequest);
        if (!currentDevice.isNormal()) {
            redirectUrlUtils.setRedirectUrl(WebUtils.getEncodeFullUrl(httpServletRequest));
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
