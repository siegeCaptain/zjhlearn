package zjh.learn.template.method;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 11501 on 2016/10/20.
 */
public abstract class AbstractSourceMethod implements TemplateMethodModelEx {

    protected abstract String getRootPath();

    @Override
    public Object exec(List list) throws TemplateModelException {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String v = list.size() > 1 ? String.valueOf(list.get(1)) : format.format(new Date());
        return String.format("%s/%s?v=%s", getRootPath(), list.get(0), v);
    }
}
