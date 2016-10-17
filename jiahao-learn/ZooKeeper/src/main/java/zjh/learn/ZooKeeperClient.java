package zjh.learn;

import org.I0Itec.zkclient.ZkClient;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by jiahao on 16-8-8.
 * 获取项目以及服务地址
 */
public class ZooKeeperClient implements ZooKeeper{

    private final String serverList;
    private final String root = "config-center";
    private final String providerName = "provider";
    private final String consumerName = "consumer";

    /**
     * key : service path
     * value: service provider address list
     */
    private final Map<String, List<String>> serviceAddressList = new HashMap<String, List<String>>();

    private ZkClient zkClient;
    private BalanceCalculator balance = new BalanceCalculator();
    private HostAddressProvider hostAddressProvider = new HostAddressProvider();

    private void init() {
        zkClient = new ZkClient(serverList);
    }

    //default zookeeper
    public ZooKeeperClient() {
        this.serverList = "127.0.0.1:2181";
        init();
    }

    //create ZooKeeperClient
    public ZooKeeperClient(String serverList) {
        this.serverList = serverList;
        init();
    }

    //register service
    @Override
    public void registerService(String project, String serviceName) throws UnknownHostException, SocketException {
        registerService(project, serviceName, hostAddressProvider.getHost());
    }

    @Override
    public void registerService(String project, String serviceName, Integer port) throws UnknownHostException, SocketException {
        registerService(project, serviceName, String.format("%s:%d", hostAddressProvider.getHost(), port));
    }

    private void registerService(String project, String serviceName, String address) {
        createServicePathIfNoExists(project, serviceName);
        updateEphemeral(root, project, serviceName, providerName, address);
    }

    //init all project & service
    private void createServicePathIfNoExists(String project, String serviceName) {
        createPersistentIfNotExists(root);
        createPersistentIfNotExists(root, project);
        createPersistentIfNotExists(root, project, serviceName);
        createPersistentIfNotExists(root, project, serviceName, providerName);
        createPersistentIfNotExists(root, project, serviceName, consumerName);
    }

    /**
     * link the project & service
     */
    private String getPath(String... paths) {
        StringBuilder path = new StringBuilder();
        for (String name : paths) {
            path.append("/");
            path.append(name);
        }
        return path.toString();
    }

    private void createPersistentIfNotExists(String... paths) {
        String path = getPath(paths);
        if(!zkClient.exists(path)) {
            zkClient.createPersistent(path);
            System.out.println(path + "created!");
        }
    }

    //delete and recreate if exists
    private void updateEphemeral(String... paths) {
        String path = getPath(paths);
        if(!zkClient.exists(path)) {
            zkClient.delete(path);
            System.out.println(path + "exists & deleted!");
        }
        zkClient.createPersistent(path);
        System.out.println(path + "created!");
    }

    //getService random
    @Override
    public String getService(String project, String serviceName) throws ZooKeeperServiceNotFoundException, UnknownHostException, SocketException {
        String serviceNode = getPath(root, project, serviceName);
        if(!serviceAddressList.containsKey(serviceNode))
            initNode(project, serviceName);

        List<String> ipList = serviceAddressList.get(serviceName);
        if(ipList.size() <= 0) throw new ZooKeeperServiceNotFoundException(serviceNode + "has no service provider!");
        return balance.randomGet(ipList);
    }

    //TODO getService with weight

    /**
     *     get all nodes
     */
    @Override
    public ZooKeeperNode getAllNodes() {
        ZooKeeperNode node = new ZooKeeperNode();
        node.setNodeName(root);
        node.setSubNodes(getNodes(getPath(root)));
        return node;
    }

    private List<ZooKeeperNode> getNodes(String nodePath) {
        List<ZooKeeperNode> nodes = new ArrayList<>();
        zkClient.getChildren(nodePath).stream().forEach(path -> {
            ZooKeeperNode subNode = new ZooKeeperNode();
            subNode.setNodeName(path);
            nodes.add(subNode);
            subNode.getSubNodes().addAll(getNodes(nodePath + "/" + path));
        });
        return nodes;
    }

    private synchronized void initNode(String project, String serviceName) throws UnknownHostException, SocketException {
        String serviceNode = getPath(root, project, serviceName, providerName);
        //再检验一次,防止等待线程重复调用.
        if (serviceAddressList.containsKey(serviceNode)) return;

        //创建consumer节点
        createServicePathIfNoExists(project, serviceName);
        updateEphemeral(root, project, serviceName, consumerName, hostAddressProvider.getHost());

        //获取服务节点并监听节点变化
        serviceAddressList.put(serviceNode, zkClient.getChildren(serviceNode));
        System.out.println(serviceName + " init " + Arrays.toString(serviceAddressList.get(serviceNode).toArray()));

        zkClient.subscribeChildChanges(serviceNode, (s, list) -> {
            serviceAddressList.put(serviceNode, list);
            System.out.println(serviceName + " init " + Arrays.toString(serviceAddressList.get(serviceNode).toArray()));
        });
    }
}
