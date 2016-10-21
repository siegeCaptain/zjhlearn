package zjh.learn.dtos;

import zjh.learn.dtos.ENUM.OrderStatus;
import zjh.learn.dtos.ENUM.PaymentMethod;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单DTO
 * Created by jiahao.zhang on 2016/10/18.
 */
public class OrderDto extends BaseEntityDto<String>{

    //订单编号
    private String sn;

    //订单状态
    private OrderStatus status;

    //订单硬币个数(5角为基数)
    private Integer coinNum;

    //支付方式
    private PaymentMethod paymentMethod;

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

    //region setter and getter
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
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
    //endregion
}
