package zjh.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import zjh.learn.Bean.PaymentConfig;

/**
 * Created by 11501 on 2016/10/18.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableConfigurationProperties ({PaymentConfig.class})
@EnableCaching
public class PaymentServiceApplication extends WebMvcConfigurerAdapter{
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
