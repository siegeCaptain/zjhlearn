package coin.store.service.Impl;

import coin.store.entity.ENUM.OrderStatus;
import coin.store.entity.ENUM.PaymentMethod;
import coin.store.entity.Order;
import coin.store.repository.OrderRepository;
import coin.store.service.OrderService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 11501 on 2016/10/18.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    // 订单默认有效期为30分钟
    private final int DEFAULT_VALID_PERIOD = 30;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 创建订单
     * @param coinNum
     *            订单硬币数量
     * @param paymentMethod
     *            支付方式
     * @param balance
     *            使用余额
     * @return
     */
    @Override
    public Order create(Integer coinNum, PaymentMethod paymentMethod, BigDecimal balance) {
        Order order = new Order();
        String uuid = UUID.randomUUID().toString();
//        while (orderRpository.findBySn(uuid) != null) {
//            uuid = UUID.randomUUID().toString();
//        }
        order.setSn(uuid);
        Date now = new Date();
        order.setCreateDate(now);
        order.setExpire(DateUtils.addMinutes(now, DEFAULT_VALID_PERIOD));
        order.setCoinNum(coinNum);
        order.setAmountPaid(BigDecimal.valueOf(0));
        order.setStatus(OrderStatus.pendingPayment);
        order.setShippedCoinNum(0);
        order.setPaymentMethod(paymentMethod);

        try {
            order = orderRepository.save(order);
        } catch (Exception e) {
            order.setStatus(OrderStatus.failed);
        }

        return order;
    }
}
