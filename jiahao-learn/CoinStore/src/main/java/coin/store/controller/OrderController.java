package coin.store.controller;

import coin.store.entity.ENUM.PaymentMethod;
import coin.store.entity.Order;
import coin.store.repository.OrderRepository;
import coin.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by 11501 on 2016/10/18.
 */
@Controller("coinStoreOrderController")
@RequestMapping("/order")
public class OrderController {

    @Resource(name = "orderServiceImpl")
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    /**
     *创建
     */
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Order create(@RequestBody Order order) {

        Order saveOrder = orderService.create(order.getCoinNum(), order.getPaymentMethod(), null);
        return saveOrder;
    }

    /**
     * 支付
     */
    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String payment(String sn, ModelMap model, RedirectAttributes redirectAttributes) {
        Order order = orderRepository.findBySn(sn);

        if (order == null || order.getPaymentMethod() == null) {

        }
        return null;
    }
}
