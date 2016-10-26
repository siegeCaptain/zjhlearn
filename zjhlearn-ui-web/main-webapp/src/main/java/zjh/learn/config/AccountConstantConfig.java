package zjh.learn.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * account 常量配置
 *
 * Created by 11501 on 2016/10/26.
 */
@ConfigurationProperties(prefix = "accountConstant")
@Component
public class AccountConstantConfig {

    private String defaultRedirectUrl;

    private String homeUrl;

    private String accountHomeUrl;

    public String getDefaultRedirectUrl() {
        return defaultRedirectUrl;
    }

    public void setDefaultRedirectUrl(String defaultRedirectUrl) {
        this.defaultRedirectUrl = defaultRedirectUrl;
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    public String getAccountHomeUrl() {
        return accountHomeUrl;
    }

    public void setAccountHomeUrl(String accountHomeUrl) {
        this.accountHomeUrl = accountHomeUrl;
    }
}
