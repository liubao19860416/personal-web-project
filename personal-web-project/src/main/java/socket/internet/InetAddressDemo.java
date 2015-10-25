package socket.internet;

import java.net.InetAddress;

/**
 * 获取网络IP相关：InetAddress.getLocalHost();// 得到本机IP
 * 
 * @author Liubao
 * @2015年3月31日
 * 
 */
public class InetAddressDemo {
    
    public static void main(String args[]) throws Exception {
        InetAddress locAdd = null;
        InetAddress remAdd = null;
        locAdd = InetAddress.getLocalHost();// 得到本机IP
        System.out.println("本机的IP地址：" + locAdd.getHostAddress());
        System.out.println("本机是否可达：" + locAdd.isReachable(5000));
        remAdd = InetAddress.getByName("https://www.baidu.com/");
        System.out.println("MLDNJAVA的IP地址：" + remAdd.getHostAddress());
    }
};