package http;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
    
    public static void main(String[] args) {
        long transIP2Int = transIP2Int("192.168.0.1");
        System.out.println(transIP2Int);
    }
    

    /**
     * 获取服务器本地的ip地址
     */
    public static String getLocalIp() {
        InetAddress ip = null;
        String localIp = "127.0.0.1";
        try {
            Enumeration<?> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                ip = (InetAddress) ni.getInetAddresses().nextElement();
                if (!ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    localIp = ip.getHostAddress();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localIp;
    }

    /**
     * 获取客户机的ip地址
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }

    /**
     * 可以用来判断IP地址是否改变了？
     */
    public static long transIP2Int(String ip) {
        String[] temp = ip.split("\\.");
        int[] ipInt = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            ipInt[i] = new Integer(temp[i]).intValue();
        }
        long ipNum = (long) ipInt[0] * 256 * 256 * 256 + ipInt[1] * 256 * 256
                + ipInt[2] * 256 + ipInt[3];
        return ipNum;
    }
    
    /**
     * 检查IP是否合法
     */

    public static boolean ipValid(String s) {
        String regex0 = "(2[0-4]\\d)" + "|(25[0-5])";
        String regex1 = "1\\d{2}";
        String regex2 = "[1-9]\\d";
        String regex3 = "\\d";
        String regex = "(" + regex0 + ")|(" + regex1 + ")|(" + regex2 + ")|("
                + regex3 + ")";
        regex = "(" + regex + ").(" + regex + ").(" + regex + ").(" + regex
                + ")";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        return m.matches();
    }

}
