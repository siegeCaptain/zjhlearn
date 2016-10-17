package zjh.learn.zooKeeper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jiahao on 16-8-4.
 * ZooKeeper负载均衡
 */
@ConfigurationProperties(prefix = "zooKeeper")
@Configuration
public class ZooKeeperConfig {

    private String hosts;
    private String project;
    private String service;
    private Integer port;

    //region getter and setter
    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
    //end region
}
