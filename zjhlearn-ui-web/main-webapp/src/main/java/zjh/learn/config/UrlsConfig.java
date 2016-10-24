package zjh.learn.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 11501 on 2016/10/24.
 */
@ConfigurationProperties(prefix = "urls")
@Configuration
public class UrlsConfig {

    private String indexUrl;
    private String acountServiceApiUrl;
    private String loginUrl;
    private String payUrl;
    private String payWechatUrl;

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public String getAcountServiceApiUrl() {
        return acountServiceApiUrl;
    }

    public void setAcountServiceApiUrl(String acountServiceApiUrl) {
        this.acountServiceApiUrl = acountServiceApiUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getPayWechatUrl() {
        return payWechatUrl;
    }

    public void setPayWechatUrl(String payWechatUrl) {
        this.payWechatUrl = payWechatUrl;
    }
}
