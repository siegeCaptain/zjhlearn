package coin.store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 支付-Controller
 *
 * Created by jiahao.zhang on 2016/10/21.
 */

@Controller("coinStorePaymentController")
@RequestMapping("/payment")
public class PaymentController {

    private static Logger logger = LoggerFactory.getLogger(PaymentController.class);

}
