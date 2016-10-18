package zjh.learn.dtos.ENUM;

/**
 * Created by 11501 on 2016/10/18.
 */
public enum OrderStatus {

    /**
     * 等待付款
     */
    pendingPayment("未支付"),

    /**
     * 等待审核
     */
    pendingReview("等待审核"),

    /**
     * 等待退币
     */
    pendingShipment("待退币"),

    /**
     * 已退币
     */
    shipped("已退币"),
    /**
     * 已完成
     */
    completed("已完成"),

    /**
     * 已失败
     */
    failed("已失败"),

    /**
     * 已取消
     */
    canceled("已取消"),

    /**
     * 已拒绝
     */
    denied("已拒绝");

    final String value;
    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getOrdinal() {
        return ordinal();
    }
}
