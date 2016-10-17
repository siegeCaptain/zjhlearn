package zjh.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import zjh.learn.Bean.AccountConfig;

/**
 * Created by jiahao on 16-8-8.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableConfigurationProperties({AccountConfig.class})
@EnableCaching
public class AccountApplication extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {

        SpringApplication.run(AccountApplication.class, args);
    }
}
