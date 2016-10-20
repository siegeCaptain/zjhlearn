package zjh.learn.service.impl;

import org.springframework.stereotype.Service;
import zjh.learn.service.PayService;
import zjh.learn.service.dtos.JsPayInput;
import zjh.learn.service.dtos.QrPayInput;

/**
 * Created by 11501 on 2016/10/20.
 */
@Service
public class PayServiceImpl implements PayService {
    @Override
    public String jsPayInfo(JsPayInput input) {
        return null;
    }

    @Override
    public String QrUrl(QrPayInput input) {
        return null;
    }
}
