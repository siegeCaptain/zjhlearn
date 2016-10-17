package zjh.learn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiahao on 16-8-8.
 * zooKeeper node info
 */
public class ZooKeeperNode {
    private String nodeName;
    private List<ZooKeeperNode> subNodes;

    public ZooKeeperNode() {
        this.subNodes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return toString(0);
    }

    /**
     * 缩进递归打印所有节点
     * @param pos
     * @return
     */
    private String toString(Integer pos) {
        String result = "";
        for (int i = 0; i < pos; i++) result += "   ";
        result += nodeName + "\r\n";
        for (ZooKeeperNode subNode : subNodes) {
            result += subNode.toString(pos + 1);
        }
        return result;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<ZooKeeperNode> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(List<ZooKeeperNode> subNodes) {
        this.subNodes = subNodes;
    }
}
