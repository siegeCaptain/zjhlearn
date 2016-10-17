package zjh.learn.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jiahao on 16-8-8.
 */
@Configuration
@ConfigurationProperties(prefix = "accountservice")
public class AccountConfig {

    private String userPasswordSalt;

    private String tokenValidPeriod;

    private String accountServiceUrl;

    public String getUserPasswordSalt() {
        return userPasswordSalt;
    }

    public void setUserPasswordSalt(String userPasswordSalt) {
        this.userPasswordSalt = userPasswordSalt;
    }

    public String getTokenValidPeriod() {
        return tokenValidPeriod;
    }

    public void setTokenValidPeriod(String tokenValidPeriod) {
        this.tokenValidPeriod = tokenValidPeriod;
    }

    public String getAccountServiceUrl() {
        return accountServiceUrl;
    }

    public void setAccountServiceUrl(String accountServiceUrl) {
        this.accountServiceUrl = accountServiceUrl;
    }
}
