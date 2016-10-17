package zjh.learn.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jiahao on 16-8-12.
 */
@Configuration
@ConfigurationProperties(prefix = "coreservice")
public class CoreConfig {
}
