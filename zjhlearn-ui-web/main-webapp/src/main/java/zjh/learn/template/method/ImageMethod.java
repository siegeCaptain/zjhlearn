package zjh.learn.template.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zjh.learn.config.CdnConfig;

/**
 * Created by 11501 on 2016/10/20.
 */
@SuppressWarnings("ALL")
@Component
public class ImageMethod extends AbstractSourceMethod {

    private final CdnConfig cdnConfig;

    @Autowired
    public ImageMethod(CdnConfig cdnConfig) {
        this.cdnConfig = cdnConfig;
    }

    @Override
    protected String getRootPath() {
        return cdnConfig.getPath().getImage();
    }
}
