package zjh.learn.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 11501 on 2016/10/24.
 */
@ConfigurationProperties(prefix = "interceptorPathPatterns")
@Configuration
public class PathPatternsConfig {
    private String[] authToken;

    public String[] getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String[] authToken) {
        this.authToken = authToken;
    }
}
