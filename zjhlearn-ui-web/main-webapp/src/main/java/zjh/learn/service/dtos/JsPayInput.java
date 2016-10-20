package zjh.learn.service.dtos;

/**
 * Created by 11501 on 2016/10/20.
 */
public class JsPayInput {

    private String code;
    private String type = "payment";
    private String sn;
    private String paymentPluginId="wechatJsPaymentPlugin";
    private String openId;

    public JsPayInput(String sn, String code,String openId) {
        this.sn = sn;
        this.code = code;
        this.openId = openId;
    }

    //region getter and setter

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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

    //endregion
}
