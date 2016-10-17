package zjh.learn;

/**
 * Created by jiahao on 16-8-8.
 */

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 提供Zookeeper服务注册,服务发现能力
 * 在使用的时候，创建出来的实例使用单例，而不要总是创建新的实例。每个实例都会跟对应的服务器端进行长链接，并且接受服务端推送。
 */
public interface ZooKeeper {
    /**
     * 注册服务，默认80端口
     *
     * @param project     项目名称,例如 ma 或者 jeepstore
     * @param serviceName 服务名称,例如 account 或者 shortlink
     * @throws UnknownHostException 注册时候获取本机地址异常
     */
    void registerService(String project, String serviceName) throws UnknownHostException, SocketException;

    /**
     * 注册服务
     *
     * @param project     项目名称
     * @param serviceName 服务名称
     * @param port        端口号
     * @throws UnknownHostException 注册时候获取本机地址异常
     */
    void registerService(String project, String serviceName, Integer port) throws UnknownHostException, SocketException;

    /**
     * 获取服务
     *
     * @param project     项目名称
     * @param serviceName 服务名称
     * @return 返回对应的服务地址
     * @throws ZooKeeperServiceNotFoundException 没有找到任何服务提供者
     * @throws UnknownHostException              注册服务消费者节点时获取本机地址异常
     */
    String getService(String project, String serviceName) throws ZooKeeperServiceNotFoundException, UnknownHostException, SocketException;

    /**
     * 获取根节点以及所有的子节点
     *
     * @return 获取根节点以及所有的子节点
     */
    ZooKeeperNode getAllNodes();

    /**
     * 获取ZooKeeper实例,默认集群地址为127.0.0.1:2181
     *
     * @return 返回对应实例
     */
    static ZooKeeper getZooKeeperInstance() {
        return new ZooKeeperClient();
    }

    /**
     * 使用集群地址创建客户端实例
     *
     * @param serverList zookeeper集群服务地址
     * @return 返回ZooKeeper对象
     */
    static ZooKeeper getZooKeeperInstance(String serverList) {
        return new ZooKeeperClient(serverList);
    }

}
