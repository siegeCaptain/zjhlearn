package coin.store.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jiahao.zhang on 2016/10/20.
 */
public interface WechatPay {
    Object getInfo(String sn, String amount, HttpServletRequest request);
}
