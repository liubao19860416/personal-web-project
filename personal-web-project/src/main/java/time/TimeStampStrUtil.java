package time;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * 取得时间戳的类
 * 
 * @author Liubao
 * @2015年3月10日
 * 
 */
public class TimeStampStrUtil {
    private Calendar calendar = null;
    // 此标记有外部决定,前缀和后缀
    private String prefix = null;
    private String suffix = null;

    public TimeStampStrUtil() {
        this.calendar = new GregorianCalendar();
    }

    public TimeStampStrUtil(String prefix) {
        this();
        this.prefix = prefix;
    }
    
    public TimeStampStrUtil(String prefix,String suffix) {
        this();
        this.prefix = prefix;
        this.suffix = suffix;
    }
    
    //TODO 测试
    public static void main(String[] args) {
        String timeStamp = new TimeStampStrUtil().getTimeStamp();
        System.out.println(timeStamp);
        timeStamp = new TimeStampStrUtil("prefix-","-suffix").getTimeStamp();
        System.out.println(timeStamp);
        timeStamp=new TimeStampStrUtil("order-").getTimeStampRandom();
        System.out.println(timeStamp);
    }

    /**
     * 获取时间戳格式的字符串，可以带前缀和后缀
     * @return
     */
    public String getTimeStamp() {
        StringBuffer buf = new StringBuffer();
        if (this.prefix != null) {
            buf.append(this.prefix);
        }
        buf.append(this.addZero(calendar.get(Calendar.YEAR), 4));
        buf.append(this.addZero(calendar.get(Calendar.MONTH) + 1, 2));
        buf.append(this.addZero(calendar.get(Calendar.DAY_OF_MONTH), 2));
        buf.append(this.addZero(calendar.get(Calendar.HOUR_OF_DAY), 2));
        buf.append(this.addZero(calendar.get(Calendar.MINUTE), 2));
        buf.append(this.addZero(calendar.get(Calendar.SECOND), 2));
        buf.append(this.addZero(calendar.get(Calendar.MILLISECOND), 3));
        if (this.suffix != null) {
            buf.append(this.suffix);
        }
        return buf.toString();
    }

    /**
     * 获取时间戳格式的字符串，可以带前缀，后面添加了3位随机数，后缀不支持；
     * @return
     */
    public String getTimeStampRandom() {
        StringBuffer buf = new StringBuffer();
        Random r = new Random();
        buf.append(this.getTimeStamp());
        buf.append(r.nextInt(10));
        buf.append(r.nextInt(10));
        buf.append(r.nextInt(10));
        return buf.toString();
    }

    // 可以单独设置一个加“0”的操作
    private String addZero(int temp, int len) {
        String str = temp + "";
        while (str.length() < len) {
            str = "0" + str;
        }
        return str;
    }
}