package time;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Calendar;

public class RemoteTimeUtil {
    
    public static void main(String[] args) throws IOException {
        System.out.println(DatetimeUtils.formatTimestamp(DatetimeUtils.currentTimestamp()));
        long netTime1 = new RemoteTimeUtil().getNetTime("http://www.rjh.com.cn/2013ruijin/ruijin/sy/index.shtml");
        System.out.println(DatetimeUtils.formatTimestamp(new Timestamp(netTime1)));
        long netTime2 =  new RemoteTimeUtil().getNetTime("http://yuyue.rjh.com.cn/Info/Terms");
       System.out.println(DatetimeUtils.formatTimestamp(new Timestamp(netTime2)));
       netTime2 =  new RemoteTimeUtil().getNetTime("http://yuyue.rjh.com.cn/User/Index");
       System.out.println(DatetimeUtils.formatTimestamp(new Timestamp(netTime2)));
       netTime2 =  new RemoteTimeUtil().getNetTime("http://yuyue.rjh.com.cn/User/MyCards");
       System.out.println(DatetimeUtils.formatTimestamp(new Timestamp(netTime2)));
       long netTime3 =  new RemoteTimeUtil().getNetTime("http://www.baidu.com");
       long netTime4 =  new RemoteTimeUtil().getNetTime("http://open.baidu.com/special/time/");
       long netTime5 =  new RemoteTimeUtil().getNetTime("http://www.114time.com/");
       System.out.println(DatetimeUtils.formatTimestamp(new Timestamp(netTime1)));
       System.out.println(DatetimeUtils.formatTimestamp(new Timestamp(netTime2)));
       System.out.println(DatetimeUtils.formatTimestamp(new Timestamp(netTime3)));
       System.out.println(DatetimeUtils.formatTimestamp(new Timestamp(netTime4)));
       System.out.println(DatetimeUtils.formatTimestamp(new Timestamp(netTime5)));
//        long netTime4 = new RemoteTimeUtil().getNetTime("http://www.12306.cn");
//        new RemoteTimeUtil().getNetTime();
    }

    public void getNetTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                while (count < 30) {
                    try {
                        // 打印百度的服务器时间
                        System.out.print("baidu:====");
                        getNetTime("http://www.baidu.com");
                        // 打印12306的服务器时间
                        System.out.print("12306:====");
                        getNetTime("http://www.12306.cn");
                        // 打印当前系统时间
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        System.out.print("system:====");
                        printTime(calendar, calendar.get(Calendar.DAY_OF_WEEK));

                        // 一秒后在执行
                        Thread.sleep(1000);
                        count++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 获取网站时间
     */
    /*public static long getNetTime(String urlStr) throws IOException {
        // 取得资源对象
        //URL url = new URL("http://www.baidu.com");
        URL url = new URL(urlStr);
        // 生成连接对象
        URLConnection uc = url.openConnection();
        uc.setConnectTimeout(1000);
        // 发出连接
        uc.connect();
        // 获取服务器时间
        Long tempTime = uc.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(tempTime);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        // 输出出来
        printTime(calendar, week);
        return tempTime;
    }*/
    /**
     * 简化版本
     */
    public static long getNetTime(String urlStr) throws IOException {
        // 取得资源对象
        URL url = new URL(urlStr);
        // 生成连接对象
        URLConnection uc = url.openConnection();
        uc.setConnectTimeout(500);
        // 发出连接
        uc.connect();
        // 获取服务器时间
        return uc.getDate();
    }

    /**
     * 打印时间
     */
    public static void printTime(Calendar calendar, int week) {
        System.out.println("Time is :t" + calendar.get(Calendar.YEAR) + "年"
                + (calendar.get(Calendar.MONTH) + 1) + "月"
                + calendar.get(Calendar.DAY_OF_MONTH) + "日"
                + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                + calendar.get(Calendar.MINUTE) + "分"
                + calendar.get(Calendar.SECOND) + "秒"
                + calendar.get(Calendar.DAY_OF_WEEK) + getWeekToString(week));
    }

    /**
     * 转化成星期
     * 
     * @param week
     * @return
     */
    public static String getWeekToString(int week) {
        String[] strArr = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四",
                "星期五", "星期六" };
        return strArr[week - 1];

    }
}
