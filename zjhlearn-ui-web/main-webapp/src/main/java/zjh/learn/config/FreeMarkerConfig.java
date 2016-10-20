package zjh.learn.config;

import freemarker.template.TemplateException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import zjh.learn.template.method.*;

import java.io.IOException;

/**
 * Created by jiahao.zhang on 2016/10/20.
 */
@Component
public class FreeMarkerConfig {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(ImageMethod image,
                                                     CssMethod cssMethod,
                                                     JsMethod jsMethod)
        throws IOException, TemplateException {
        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
        factory.setTemplateLoaderPaths("classpath:templates", "src/main/resource/templates");
        factory.setDefaultEncoding("UTF-8");
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        freemarker.template.Configuration configuration = factory.createConfiguration();
        result.setConfiguration(configuration);

        configuration.setSharedVariable("image", image);
        configuration.setSharedVariable("css", cssMethod);
        configuration.setSharedVariable("js", jsMethod);
        return result;
    }
}
