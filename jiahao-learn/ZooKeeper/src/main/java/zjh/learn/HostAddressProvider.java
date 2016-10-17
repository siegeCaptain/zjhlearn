package zjh.learn;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

/**
 * Created by jiahao on 16-8-8.
 */
public class HostAddressProvider {
    private List<String> getHosts() throws UnknownHostException, SocketException {
        List<String> hosts = new ArrayList<>();
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress ip = inetAddresses.nextElement();
                if (ip.isLoopbackAddress() || ip.getHostAddress().contains(":")) continue;
                hosts.add(ip.getHostAddress());
            }
        }
        return hosts;
    }

    String[] prefixes = {"192.", "10."};

    public String getHost() throws SocketException, UnknownHostException {
        List<String> hosts = getHosts();
        String loopbackAddress = "127.0.0.1";
        if (hosts.size() <= 0) return loopbackAddress;

        for (String prefix : prefixes) {
            if (getHost(hosts, prefix) != null) return getHost(hosts, prefix);
        }
        return hosts.get(0);
    }

    public String getHost(List<String> list, String prefix) {
        Optional<String> ip = list.stream().filter(c -> c.startsWith(prefix)).findFirst();
        if (ip.isPresent()) return ip.get();
        return null;
    }
}
