package http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress相关方法测试
 * 
 * @author Liubao
 * @2014年12月2日
 * 
 */
public class InetAddressTest {

    public static void main(String[] args) throws IOException {

        InetAddress ip = InetAddress.getByName("www.baidu.com");
        System.out.println(ip.getHostName());
        System.out.println(ip.getHostAddress());
        System.out.println(ip.getCanonicalHostName());

        ip = InetAddress.getByName("localhost");
        System.out.println(ip.getHostName());
        System.out.println(ip.getHostAddress());
        System.out.println(ip.getCanonicalHostName());

        ip = InetAddress.getByName("Liubao");
        System.out.println(ip.getHostName());
        System.out.println(ip.getHostAddress());
        System.out.println(ip.getCanonicalHostName());

        ip = InetAddress.getByName("192.168.1.101");
        System.out.println(ip.getHostName());
        System.out.println(ip.getHostAddress());
        System.out.println(ip.getCanonicalHostName());

        ip = InetAddress.getByName("127.0.0.1");
        System.out.println(ip.getHostName());
        System.out.println(ip.getHostAddress());
        System.out.println(ip.getCanonicalHostName());

        ip = InetAddress.getLocalHost();
        System.out.println(ip.getHostName());
        System.out.println(ip.getHostAddress());

        ip = InetAddress.getByName("119.75.218.77");
        System.out.println(ip.getHostName());
        System.out.println(ip.getHostAddress());

    }

}
