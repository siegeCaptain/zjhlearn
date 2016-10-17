package zjh.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import zjh.learn.Bean.CoreConfig;

/**
 * Created by jiahao on 16-8-5.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableConfigurationProperties({CoreConfig.class})
@EnableCaching
public class CoreServiceApplication extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(CoreServiceApplication.class, args);
    }
}
