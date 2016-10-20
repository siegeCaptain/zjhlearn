package zjh.learn.service.dtos;

/**
 * Created by 11501 on 2016/10/20.
 */
public class QrPayInput {

    private String sn;
    private String type="payment";
    private String paymentPluginId="wechatScanPaymentPlugin";
    private String openId;//wechat openId

    public QrPayInput(String sn,String openId) {
        this.sn = sn;
        this.openId = openId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaymentPluginId() {
        return paymentPluginId;
    }

    public void setPaymentPluginId(String paymentPluginId) {
        this.paymentPluginId = paymentPluginId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
