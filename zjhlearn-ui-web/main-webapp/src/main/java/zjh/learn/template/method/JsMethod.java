package zjh.learn.template.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zjh.learn.config.CdnConfig;

/**
 * Created by 11501 on 2016/10/20.
 */
@Component
public class JsMethod extends AbstractSourceMethod{

    private final CdnConfig cdnConfig;

    @Autowired
    public JsMethod(CdnConfig cdnConfig) {
        this.cdnConfig = cdnConfig;
    }

    @Override
    protected String getRootPath() {
        return cdnConfig.getPath().getScripts();
    }
}
