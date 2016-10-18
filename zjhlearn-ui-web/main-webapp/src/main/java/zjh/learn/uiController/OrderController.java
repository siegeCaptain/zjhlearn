package zjh.learn.uiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import zjh.learn.dtos.ENUM.OrderStatus;
import zjh.learn.dtos.OrderDto;

/**
 * Created by 11501 on 2016/10/18.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(path = "/payment/wechatQr")
    public String qrImg(String orderSn, ModelMap model) {
//        OrderDto order = restTemplate.getForObject(url("{sn}?customerId={customerId}"), OrderDto.class, orderSn, getCustomerId());
//        if (!OrderStatus.pendingPayment.equals(order.getStatus()))
//            return "redirect:/order/account/list/unpaid";
//        model.put("qrImg", payService.QrUrl(new QrPayInput(orderSn, 1)));
//        model.put("order", order);
        return "order/wechatQr";
    }
}
