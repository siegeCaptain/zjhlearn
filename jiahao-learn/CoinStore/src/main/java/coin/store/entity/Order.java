package coin.store.entity;

import coin.store.entity.ENUM.OrderStatus;
import coin.store.entity.ENUM.PaymentMethod;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 11501 on 2016/10/18.
 */
@Document(collection = "orders")
public class Order extends BaseEntity<String> {

    //订单编号
    private String sn;

    //订单状态
    private OrderStatus status;

    //订单硬币个数(5角为基数)
    private Integer coinNum;

    /**
     * 已付款金额
     */
    private BigDecimal amountPaid;

    /**
     * 已退币数量
     */
    private Integer shippedCoinNum;

    /**
     * 过期时间
     */
    private Date expire;

    /**
     *支付方式
     */
    private PaymentMethod paymentMethod;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(Integer coinNum) {
        this.coinNum = coinNum;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Integer getShippedCoinNum() {
        return shippedCoinNum;
    }

    public void setShippedCoinNum(Integer shippedCoinNum) {
        this.shippedCoinNum = shippedCoinNum;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Order{" +
                "sn='" + sn + '\'' +
                ", status=" + status +
                ", coinNum=" + coinNum +
                ", amountPaid=" + amountPaid +
                ", shippedCoinNum=" + shippedCoinNum +
                ", expire=" + expire +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
