package zjh.learn.uiController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import zjh.learn.dtos.ENUM.OrderStatus;
import zjh.learn.dtos.OrderDto;
import zjh.learn.service.PayService;
import zjh.learn.service.dtos.JsPayInput;
import zjh.learn.service.dtos.QrPayInput;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiahao.zhang on 2016/10/18.
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

    /**
     * 获取订单信息
     * @param sn
     * @return 订单信息
     */
    @RequestMapping(value = "/{sn}", method = RequestMethod.GET)
    @ResponseBody
    public OrderDto get(@PathVariable String sn) {
        return restTemplate.getForObject(url("{sn}"), OrderDto.class, sn);
    }

    /**
     *创建订单
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> create(@RequestBody OrderDto inputOrder) {
        Map<String, Object> data = new HashMap<String, Object>();
        OrderDto savedOrder = restTemplate.postForObject("http://127.0.0.1:7007/order/create", inputOrder, OrderDto.class);
        data.put("sn", savedOrder.getSn());
        data.put("paymentMethod", savedOrder.getPaymentMethod());
        return data;
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

    /**
     *微信扫描二维码支付
     */
    @RequestMapping(path = "/payment/wechatQr")
    public String wechatQrPayment(String orderSn, ModelMap model) {
        OrderDto order = restTemplate.getForObject(url("{sn}"), OrderDto.class, orderSn);
//        if (!OrderStatus.pendingPayment.equals(order.getStatus()))
//            return "redirect:/order/account/list/unpaid";
        model.put("order", order);
        model.put("amount", new BigDecimal(order.getCoinNum() * 0.5));
        return "order/wechatQr";
    }

    /**
     * 订单支付成功页面
     *
     * @param orderSn 订单号
     */
    @RequestMapping(path = "/payment/success")
    public String paySuccess(String orderSn, ModelMap model) {
        OrderDto order = restTemplate.getForObject(url("{sn}"), OrderDto.class, orderSn);
        model.put("order", order);
        return "order/pay-success";
    }

    /**
     *请求二维码图片流
     */
    @RequestMapping(value = "/payment/qrimage")
    public void qrImage(String ordersn, HttpServletResponse response) {
        try {
            ImageIO.write(payService.QrUrl(new QrPayInput(ordersn, getOpenId())), "png", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String url(String path) {
        if (!path.startsWith("/") && !path.startsWith("?"))
            path = "/" + path;
        return String.format("http://127.0.0.1:7007/order%s", path);
    }

    //// TODO: 2016/10/20 获取用户微信号
    private String getOpenId() {
        String openId = "wechat_openid";
        return openId;
    }
}
