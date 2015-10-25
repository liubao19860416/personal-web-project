package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 格式化日期Date工具类
 * 默认格式FORMAT_LONG使用 "yyyy-MM-dd HH:mm:ss" 格式化日期
 * 
 */
public final class DateUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    /**
     * 英文全称 如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    /**
     * 中文简写 如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
    /**
     * 中文全称 如：2010年12月01日 23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    
    /**
     * 判断是否是日期格式
     */
    public static boolean isDate(String value, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            formatter.setLenient(false);
            formatter.parse(value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 获得默认的 date pattern
     */
    public static String getDefaultDatePattern() {
        return FORMAT_LONG;
    }

    /**
     * 根据预设格式返回当前日期
     */
    public static String getNow() {
        return format(new Date());
    }
    
    /**
     * 使用预设格式格式化日期
     */
    public static String format(Date date) {
        return format(date, getDefaultDatePattern());
    }
    
    /**
     * 根据用户格式返回当前日期
     */
    public static String getNow(String pattern) {
        return format(new Date(), pattern);
    }
    
    /**
     * 使用用户格式格式化日期
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            returnValue = sdf.format(date);
        }
        return (returnValue);
    }

    /**
     * 使用预设格式提取字符串日期
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDefaultDatePattern());
    }

    /**
     * 使用用户格式提取字符串日期
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            logger.info("日期格式化失败！"+pattern + e);
            return null;
        }
    }

    /**
     * 在日期上增加数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }
    
    /**
     * 在日期上增加天数,加1天就是明天；
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }
    
    /**
     * 在日期上增加天数,加1天就是明天；
     * 且设置日期的时分秒为23：59：59格式
     */
    public static Date addDayMaxHMS(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    
    /**
     * 设置日期的时分秒为23：59：59格式
     */
    public static Date setMaxHMSInDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }


    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_LONG);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取日期年份
     */
    public static String getYear(Date date) {
        return format(date).substring(0, 4);
    }

    /**
     * 按默认格式的字符串距离今天的天数（取整数）
     */
    public static int countDays(String date) {
        Calendar c = Calendar.getInstance();
        long t = c.getTime().getTime();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 按用户格式字符串距离今天的天数
     */
    public static int countDays(String date, String pattern) {
        Calendar c = Calendar.getInstance();
        long t = c.getTime().getTime();
        c.setTime(parse(date, pattern));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 根据传入的日期，获取对应的星期
     */
    public static String getWeekOfDate(Date date) { 
        Map<Integer, String> weeks = new HashMap<Integer, String>();
        weeks.put(0, "星期日");
        weeks.put(1, "星期一");
        weeks.put(2, "星期二");
        weeks.put(3, "星期三");
        weeks.put(4, "星期四");
        weeks.put(5, "星期五");
        weeks.put(6, "星期六");
        
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date); 
        int weekIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
        return weeks.get(weekIndex); 
    } 

    /**
     * 获取当前时间的小时
     */
    public static int getCurrentHour(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    
    /**
     * 获取当前时间的分钟
     */
    public static int getCurrentMinute(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }
    
    /**
     * 获取当前时间的秒数
     */
    public static int getCurrentSecond(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.SECOND);
    }
    
    /**
     * 按照指定格式，格式化指定的日期。返回一个两者相加的字符串
     */
    public static String productRule(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        StringBuffer buffer = new StringBuffer(pattern);
        buffer.append(sdf.format(date));
        return buffer.toString();
    }
    
    /**
     * 取得当前时间的 间隔多少小时之后的时间
     */
    public static String getDateAdd(int hour) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.set(Calendar.HOUR, hour + calendar.get(Calendar.HOUR));
        String enddate = sd.format(calendar.getTime());
        return enddate;

    }

    /**
     * 取得当前时间的 间隔多少小时之后的时间
     */
    public static Date getDateAdd(Date starttime, int hour) {
        Calendar calendar = Calendar.getInstance();
        java.text.SimpleDateFormat sd = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        calendar.setTime(starttime);
        calendar.set(Calendar.HOUR, hour + calendar.get(Calendar.HOUR));
        return calendar.getTime();

    }
    
    public static void main(String[] args) {
       System.out.println( productRule(new Date(),"yyyyMMddHHmmss"));
       System.out.println( productRule(new Date(),"yyyyMMddHHmmssS"));
    }
    
}