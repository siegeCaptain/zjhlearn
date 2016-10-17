package zjh.learn.zooKeeper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import zjh.learn.ZooKeeper;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * Created by jiahao on 16-8-4.
 */
@Component
public class ZooKeeperBean {

    private final ZooKeeperConfig zooKeeperConfig;
    private final static Logger logger = LoggerFactory.getLogger(ZooKeeperBean.class);

    @Autowired
    public ZooKeeperBean(ZooKeeperConfig zooKeeperConfig) {
        this.zooKeeperConfig = zooKeeperConfig;
    }

    @Bean
    public ZooKeeper getZooKeeper() throws UnknownHostException, SocketException {
        if (StringUtils.isEmpty(zooKeeperConfig.getHosts())) {
            logger.warn("no zookeeper config!this service can't use zookeeper service!");
            return null;
        }

        ZooKeeper zooKeeper = ZooKeeper.getZooKeeperInstance(zooKeeperConfig.getHosts());
        if (Objects.nonNull(zooKeeperConfig.getService()))
            zooKeeper.registerService(zooKeeperConfig.getProject(), zooKeeperConfig.getService(), zooKeeperConfig.getPort());
        else
            logger.debug("no service name will not register service for this app!");
        logger.info(zooKeeper.getAllNodes().toString());
        return zooKeeper;
    }
}
