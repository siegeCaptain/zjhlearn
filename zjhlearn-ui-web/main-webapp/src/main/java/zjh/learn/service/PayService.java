package zjh.learn.service;

import zjh.learn.service.dtos.JsPayInput;
import zjh.learn.service.dtos.QrPayInput;

/**
 * Created by 11501 on 2016/10/20.
 */
public interface PayService {

    String jsPayInfo(JsPayInput input);

    String QrUrl(QrPayInput input);
}
