package zjh.learn.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import zjh.learn.bean.User;
import zjh.learn.config.UrlsConfig;
import zjh.learn.dtos.ValidateInputDto;
import zjh.learn.dtos.ValidateOutputDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jiahao.zhang on 2016/10/24.
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserUtils {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UrlsConfig urlsConfig;

    private User currentUser = null;

    public boolean isLogin() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(HttpServletRequest request) {
        Device currentDevice = DeviceUtils.getCurrentDevice(request);

        String authToken = WebUtils.getAuthToken(request);
        if (!StringUtils.isEmpty(authToken)) {

            ValidateInputDto inputDto = new ValidateInputDto();
            inputDto.setKey(authToken);
            inputDto.setIp(WebUtils.getUserIp(request));
            inputDto.setSource(currentDevice.isMobile() ? "shop_wap" : "shop_web");

            ValidateOutputDto result = restTemplate.postForObject(
                    String.format("%s/api/token/validate", urlsConfig.getAcountServiceApiUrl()),
                    inputDto, ValidateOutputDto.class);

            if (result.getStatus() == 1) {
                currentUser = new User();
                currentUser.setUserId(result.getUserId());
                currentUser.setPhone(result.getPhone());
                currentUser.setOpenId(result.getOpenId());
            }
        }
    }
}
