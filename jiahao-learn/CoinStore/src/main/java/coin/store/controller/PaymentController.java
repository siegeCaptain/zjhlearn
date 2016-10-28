package coin.store.controller;

import coin.store.entity.PaymentLog;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * 支付-Controller
 *
 * Created by jiahao.zhang on 2016/10/21.
 */

@Controller("coinStorePaymentController")
@RequestMapping("/payment")
public class PaymentController {

    private static Logger logger = LoggerFactory.getLogger(PaymentController.class);

    /**
     * 插件提交
     */
    @RequestMapping(value = "/plugin_submit", method = RequestMethod.POST)
    public String pluginSubmit(PaymentLog.Type type, String sn, String paymentPluginId, String openId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        logger.info("type:{},paymentPluginId:{},sn:{},code:{}", type, paymentPluginId, sn, request.getParameter("code"));
        return "/shop/${theme}/payment/plugin_submit";
    }
}
