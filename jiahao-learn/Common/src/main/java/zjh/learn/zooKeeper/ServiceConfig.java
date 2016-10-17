package zjh.learn.zooKeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import zjh.learn.ZooKeeper;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 所有服务的配置以及节点名称
 *
 * @author jiahao
 * @version 2016/8/4.
 */
@ConfigurationProperties(prefix = "service")
@Component
public class ServiceConfig {

    private String shortLinkService;
    private String projectName;

    private final ZooKeeper zooKeeper;

    @Autowired
    public ServiceConfig(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }


    public String getServiceHost(String serviceName) {
        try {
            return String.format("http://%s", zooKeeper.getService(projectName, serviceName));
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException("get service host from zookeeper failed!");
        }
    }

    //region getter and setter

    public String getShortLinkService() {
        return shortLinkService;
    }

    public void setShortLinkService(String shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    //endregion
}
