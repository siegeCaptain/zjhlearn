package zjh.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zjh.learn.service.PayService;
import zjh.learn.service.dtos.JsPayInput;
import zjh.learn.service.dtos.QrPayInput;
import zjh.learn.utils.GenerateQrCodeUtil;

import java.awt.image.BufferedImage;

/**
 * Created by jiahao.zhang on 2016/10/20.
 */
@Service
public class PayServiceImpl implements PayService {

    private final RestTemplate restTemplate;

    @Autowired
    public PayServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String jsPayInfo(JsPayInput input) {
        String url = String.format("%s/payment/plugin_submit.jhtml?code=%s&type=%s&sn=%s&paymentPluginId=%s&openId=%s",
//todo                serviceConfig.coinStoreService.payServiceProvider.getHost(),
                "http://127.0.0.1:7007",
                input.getCode(),
                input.getType(),
                input.getSn(),
                input.getPaymentPluginId(),
                input.getOpenId());

        return restTemplate.postForObject(url, input, String.class);
    }

    @Override
    public BufferedImage QrUrl(QrPayInput input) {
        String url = String.format("%s/payment/plugin_submit.jhtml?type=%s&sn=%s&paymentPluginId=%s&openId=%s",
                "http://127.0.0.1:7007",
                input.getType(),
                input.getSn(),
                input.getPaymentPluginId(),
                input.getOpenId());
        // TODO: 2016/10/20 远程调用二维码生成服务 将参数url转换生成二维码图片的url
        BufferedImage image = GenerateQrCodeUtil.encodeQrcode(url);
        return image;
    }
}
