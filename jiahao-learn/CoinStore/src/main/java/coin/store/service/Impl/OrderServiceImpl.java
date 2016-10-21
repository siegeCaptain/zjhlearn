package coin.store.service.Impl;

import coin.store.entity.ENUM.OrderStatus;
import coin.store.entity.ENUM.PaymentMethod;
import coin.store.entity.Order;
import coin.store.repository.OrderRepository;
import coin.store.service.OrderService;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by 11501 on 2016/10/18.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

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

    /**
     * 释放过期订单已分配库存并取消订单
     */
    @Override
    public void releaseExpiredAllocatedStockAndCancel() {

        int countOrder = 0;
        while (true) {
            List<Order> orders = orderRepository.findAll();
            if (!CollectionUtils.isEmpty(orders)) {
                for (Order order : orders) {
                    try {
                        if ((new Date()).after(order.getExpire())) {
                            orderRepository.delete(order);
                        }
                    } catch (Exception e) {
                        logger.error("release expire order error. {sn}", order.getSn());
                    }
                }
            }
            countOrder += orders.size();
            if (orders.size() < 100) {
                break;
            }
        }
        logger.info("Automatic task release timeout order: " + countOrder + "");
    }
}
