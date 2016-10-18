package coin.store.entity.ENUM;

/**
 * Created by 11501 on 2016/10/18.
 */
public enum  PaymentMethod {

    wechat("微信支付"),

    alipay("支付宝支付");

    final String value;
    PaymentMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getOrdinal() {
        return ordinal();
    }
}
