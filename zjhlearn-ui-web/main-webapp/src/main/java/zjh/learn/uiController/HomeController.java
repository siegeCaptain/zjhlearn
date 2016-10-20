package zjh.learn.uiController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jiahao.zhang on 2016/10/18.
 */
@Controller
public class HomeController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "home/index";
    }
}
