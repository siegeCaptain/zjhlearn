package zjh.learn;


import java.io.IOException;

/**
 * Created by jiahao on 16-8-8.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ZooKeeper zooKeeper = ZooKeeper.getZooKeeperInstance("192.168.5.188:2182");
        zooKeeper.registerService("devjiahao", "account-service");
        zooKeeper.getService("devjiahao", "account-service");
        System.out.println(zooKeeper.getAllNodes().toString());
    }
}
