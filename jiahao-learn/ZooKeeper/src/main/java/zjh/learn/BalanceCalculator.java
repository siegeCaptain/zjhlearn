package zjh.learn;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by jiahao on 16-8-8.
 * 负载均衡
 */
public class BalanceCalculator {

    /**
     * 随机算法
     */
    public String randomGet(Map<String, Integer> iaddressList) {
        throw new NotImplementedException();
    }

    public String randomGet(List<String> ipList) {
        List<String> list = new ArrayList<>();
        list.addAll(ipList);

        int pos = new Random().nextInt(list.size());
        return list.get(pos);
    }
}
