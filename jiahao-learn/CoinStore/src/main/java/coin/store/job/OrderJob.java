package coin.store.job;

import coin.store.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 自动任务-订单
 *
 * Created by 11501 on 2016/10/21.
 */
@Component("orderJob")
public class OrderJob {

    Logger logger = LoggerFactory.getLogger(OrderJob.class);

    @Resource(name = "orderServiceImpl")
    private OrderService orderService;

    @Async
    public void expiredProcessing() {
        logger.info("begin expire order process:{}", new Date());
        orderService.releaseExpiredAllocatedStockAndCancel();
        logger.info("finish expire order process:{}", new Date());
    }
}
