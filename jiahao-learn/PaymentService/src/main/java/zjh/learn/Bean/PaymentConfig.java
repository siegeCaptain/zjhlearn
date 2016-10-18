package zjh.learn.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jiahao.zhang on 2016/10/18.
 */
@Configuration
@ConfigurationProperties(prefix = "paymentservice")
public class PaymentConfig {
}
