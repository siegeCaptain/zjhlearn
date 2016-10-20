package zjh.learn.uiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import zjh.learn.dtos.ENUM.OrderStatus;
import zjh.learn.dtos.OrderDto;
import zjh.learn.service.PayService;
import zjh.learn.service.dtos.JsPayInput;
import zjh.learn.service.dtos.QrPayInput;

/**
 * Created by 11501 on 2016/10/18.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private RestTemplate restTemplate;

    private PayService payService;

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(RestTemplate restTemplate,
                           PayService payService) {
        this.restTemplate = restTemplate;
        this.payService = payService;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public OrderDto create(@RequestBody OrderDto inputOrder) {
        OrderDto savedOrder = restTemplate.postForObject("http://127.0.0.1:7007/order/create", inputOrder, OrderDto.class);
        return savedOrder;
    }

    @RequestMapping(path = "/payment")
    public String payment(Device device, String orderSn, String code, String state, ModelMap model) {
        logger.info("pay:orderSn:{},code:{},state:{}", orderSn, code, state);
        if (!StringUtils.isEmpty(code)) {
            String payInfo = payService.jsPayInfo(new JsPayInput(state, code, getOpenId()));
            model.put("payInfo", payInfo);
            orderSn = state;
            logger.info(payInfo);
        }
        model.put("payUrl", "www.coinsyore.com");
        OrderDto order = restTemplate.getForObject(url("{sn}?openId={openId}"), OrderDto.class, orderSn, getOpenId());
        if(!OrderStatus.pendingPayment.equals(order.getStatus()))
            return "redirect:/order/account/list/unpaid";
        model.put("order", order);
        return "order/payment";
    }

    @RequestMapping(path = "/payment/wechatQr")
    public String qrImg(String orderSn, ModelMap model) {
        OrderDto order = restTemplate.getForObject(url("{sn}?openId={openId}"), OrderDto.class, orderSn, getOpenId());
        if (!OrderStatus.pendingPayment.equals(order.getStatus()))
            return "redirect:/order/account/list/unpaid";
        model.put("qrImg", payService.QrUrl(new QrPayInput(orderSn, getOpenId())));
        model.put("order", order);
        return "order/wechatQr";
    }

    private String url(String path) {
        if (!path.startsWith("/") && !path.startsWith("?"))
            path = "/" + path;
        return String.format("%s/api/v1/order%s", "http://127.0.0.1:7007", path);
    }

    //// TODO: 2016/10/20 获取用户微信号
    private String getOpenId() {
        String openId = "wechat_openid";
        return openId;
    }
}
