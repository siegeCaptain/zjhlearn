package zjh.learn.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jiahao on 2016/10/20.
 */
@ConfigurationProperties(prefix = "cdn")
@Configuration
public class CdnConfig {

    private Path path;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
