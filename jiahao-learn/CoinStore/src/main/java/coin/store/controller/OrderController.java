package coin.store.controller;

import coin.store.dtos.OrderDto;
import coin.store.entity.ENUM.OrderStatus;
import coin.store.entity.Order;
import coin.store.repository.OrderRepository;
import coin.store.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Order create(@RequestBody Order order) {

        Order saveOrder = orderService.create(order.getCoinNum(), order.getPaymentMethod(), null);
        return saveOrder;
    }

    /**
     * 获取订单信息
     */
    @RequestMapping(value = "/{sn}", method = RequestMethod.GET)
    @ResponseBody
    public OrderDto getOrderInfo(@PathVariable("sn") String sn) {
        Order order = orderRepository.findBySn(sn);
        ModelMapper mapper = new ModelMapper();
        return mapper.map(order, OrderDto.class);
    }

    /**
     * 改变订单状态
     *
     * @param sn     订单Sn
     * @param status 订单状态
     * @return 订单Sn
     */
    @RequestMapping(value = "/{sn}/{status}", method = RequestMethod.POST)
    public void changeStatus(@PathVariable("sn") String sn, @PathVariable("status") OrderStatus status) {
        Order order = orderRepository.findBySn(sn);
        switch (status) {
            case canceled:
                //orderService.cancel(order);
                //客户已下单未支付，客户主动取消订单(num=3)
                //notificationService.noticeOrderStatus(order,NotificationService.CUSTOMER_CANCEL_ORDER);
                break;
            case pendingShipment:
                orderService.updateStatus(order, status);
                break;
            case completed:
                orderService.updateStatus(order, status);
                break;
            default:
                break;
        }
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
