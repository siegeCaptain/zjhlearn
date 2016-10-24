package zjh.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import zjh.learn.config.PathPatternsConfig;
import zjh.learn.interceptor.AuthTokenInterceptor;
import zjh.learn.interceptor.GlobalInterceptor;

import java.nio.charset.Charset;

/**
 * Created by 11501 on 2016/10/18.
 */

@SpringBootApplication
@EnableAutoConfiguration
public class MainWebApplication extends WebMvcConfigurerAdapter{

    public static void main(String[] args) {
        SpringApplication.run(MainWebApplication.class, args);
    }

    @Autowired
    private GlobalInterceptor globalInterceptor;
    @Autowired
    private AuthTokenInterceptor authTokenInterceptor;
    @Autowired
    private PathPatternsConfig pathPatternsConfig;

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor).addPathPatterns("/**").excludePathPatterns("/error");

        String[] authTokenPathPatterns = pathPatternsConfig.getAuthToken();
        for (String pathPattern : authTokenPathPatterns) {
            registry.addInterceptor(authTokenInterceptor).addPathPatterns(pathPattern);
        }

        super.addInterceptors(registry);
    }
}
