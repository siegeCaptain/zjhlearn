package coin.store.service;

import coin.store.entity.ENUM.OrderStatus;
import coin.store.entity.ENUM.PaymentMethod;
import coin.store.entity.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 11501 on 2016/10/18.
 */
@Service
public interface OrderService {

    /**
     * 订单创建
     *
     * @param coinNum
     *            订单硬币数量
     * @param paymentMethod
     *            支付方式
     * @param balance
     *            使用余额
     * @return 订单
     */
    Order create(Integer coinNum, PaymentMethod paymentMethod, BigDecimal balance);

    /**
     * 释放过期订单已分配库存并取消订单
     */
    void releaseExpiredAllocatedStockAndCancel();

    void updateStatus(Order order, OrderStatus status);
}
