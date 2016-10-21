package coin.store.entity;

import coin.store.entity.baseEntity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entity-支付记录
 *
 * Created by jiahao.zhang on 2016/10/21.
 */
@Entity
@Table(name = "payment_log")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_payment_log")
@Document(collection = "payment_log")
public class PaymentLog extends BaseEntity<Long>{

    private static final long serialVersionUID = 5255366059286171687L;

    //类型
    public enum Type {
        //账户充值
        recharge,
        //订单支付
        payment
    }

    //状态
    public enum Status {
        //待支付
        wait,
        //支付成功
        success,
        //支付失败
        failure
    }

    /** 编号 */
    private String sn;

    /** 类型 */
    private PaymentLog.Type type;

    /** 状态 */
    private PaymentLog.Status status;

    /** 支付手续费 */
    private BigDecimal fee;

    /** 支付金额 */
    private BigDecimal amount;

    /** 支付插件ID */
    private String paymentPluginId;

    /** 支付插件名称 */
    private String paymentPluginName;

    /** 用户账号 */
    private String customerId;

    /** 订单 */
    private Order order;

    @Column(nullable = false, updatable = false, unique = true)
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Column(nullable = false, updatable = false)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Column(nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(nullable = false, updatable = false, precision = 21, scale = 6)
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Column(nullable = false, updatable = false, precision = 21, scale = 6)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @JoinColumn(nullable = false, updatable = false)
    public String getPaymentPluginId() {
        return paymentPluginId;
    }

    public void setPaymentPluginId(String paymentPluginId) {
        this.paymentPluginId = paymentPluginId;
    }

    @JoinColumn(nullable = false, updatable = false)
    public String getPaymentPluginName() {
        return paymentPluginName;
    }

    public void setPaymentPluginName(String paymentPluginName) {
        this.paymentPluginName = paymentPluginName;
    }

    @Column(nullable = true, updatable = false)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders", updatable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * 获取有效金额
     *
     * @return 有效金额
     */
    @Transient
    public BigDecimal getEffectiveAmount() {
        BigDecimal effectiveAmount = getAmount().subtract(getFee());
        return effectiveAmount.compareTo(BigDecimal.ZERO) >= 0 ? effectiveAmount : BigDecimal.ZERO;
    }
}
