package zjh.learn.service;

import zjh.learn.service.dtos.JsPayInput;
import zjh.learn.service.dtos.QrPayInput;

import java.awt.image.BufferedImage;

/**
 * Created by 11501 on 2016/10/20.
 */
public interface PayService {

    String jsPayInfo(JsPayInput input);

    BufferedImage QrUrl(QrPayInput input);
}
