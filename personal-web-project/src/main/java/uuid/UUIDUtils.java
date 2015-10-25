package uuid;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * UUID工具类
 * 
 * @author Liubao
 * @2014年12月8日
 */
public class UUIDUtils{
    private static int IP;
    private static final String sep = "_";//默认的分隔符
    private static final int DEFAULT_LENGTH = 8;

    //测试方法
    public static void main(String[] str) {
        // 得到一个36位序号
        System.out.println(generate32UUID());
        System.out.println(generate36UUIDByParams());
        
        // 一次得到多个序号
       List<String> UUIDs = generate32UUID(10);
        for (int i = 0; i < UUIDs.size(); i++) {
            System.out.println(UUIDs.get(i));
        }
    }

    //静态初始化，获取当前IP地址信息
    static {
        try {
            IP = IPToInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            IP = 0;
        }
    }
    private static int IPToInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }

    /**
     * 格式化int值，在开头部分补充0满8位
     */
    protected static String format(int intval) {
        return format(intval, DEFAULT_LENGTH);
    }
    
    /**
     * 格式化int值，在开头部分补充0满4位
     */
    protected static String format(int intval,int length) {
        String formatted = Integer.toHexString(intval);
        String str="";
        switch (length) {
        case 4:
            str="0000";
            break;
        default:
            str="00000000";
            break;
        }
        StringBuffer buf = new StringBuffer(str);
        if(length>=formatted.length()){
            buf.replace(formatted.length()-length, length, formatted);
        }else{
           formatted.substring(length);
        }
        return buf.toString();
    }
    /**
     * 格式化short值，在开头部分补充0满8位
     */
    protected static String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    protected static int getIP() {
        return IP;
    }
    
    protected static int getRanndomInt() {
        return new Random().nextInt();
    }

    protected static short getShortTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    protected static int getIntTime() {
        return (int) System.currentTimeMillis();
    }

    // ========================公开的静态方法====================

    
    /**
     *  得到一个36位序号，其中有4个自定义分隔符
     */
    public static String generate36UUIDByParams() {
        return new StringBuffer(36).append(format(getIP())).append(sep)
                .append(format(getRanndomInt(),4)).append(sep)
                .append(format(getRanndomInt(),4)).append(sep)
                .append(format(getShortTime())).append(sep)
                .append(format(getIntTime())).toString();
    }

    /**
     *  得到一个36位序号，其中有4个默认分隔符
     */
    public static String generate36UUID() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * 得到一个32位序号，没有分隔符
     */
    public static String generate32UUID() {
        String s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
                + s.substring(19, 23) + s.substring(24);
    }
    

    /**
     * 一次得到number个32位数字，没有分隔符
     */
    public static List<String> generate32UUID(int number) {
        if (number <= 1) {
            number=1;
        }
        List<String> list=new ArrayList<String>(number);
        for (int i = 0; i < number; i++) {
            list.add(generate32UUID());
        }
        return list;
    }

}
