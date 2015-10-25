package time.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * 测试设置日期格式23：59：59
 * 
 * 测试StringUtils工具类的“”和非空判断
 * 
 * @author Liubao
 * @2014年11月7日
 * 
 */
public class TestTimestamp {

    @Test
    public void test0() throws Exception {
        Date date = new Date();
        String dateFormatStr = "\tH:mm:ss\n\tE\n\tyyyy-M-dd";
        SimpleDateFormat simpleFormat = new SimpleDateFormat(dateFormatStr);
        System.out.println(simpleFormat.format(date));
    }
    
    
    @Test
    public void test00() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String week = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1);

        System.out.println("年：" + year + "月：" + month + "日：" + day + ",星期："
                + week);

        long year2009 = calendar.getTimeInMillis();
        calendar.set(1984, 0, 17);
        long year1984 = calendar.getTimeInMillis();
        long days = (year2009 - year1984) / (1000 * 60 * 60 * 24);
        System.out.println("2009--1984:" + days + "!");

    }

    @Test
    public void test01() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2010-11-05 18:22:11");
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.DATE, 4);
        System.out.println(sdf.format(cl.getTime()));
    }

    @SuppressWarnings("deprecation")
    @Test
    public void test02() throws Exception {
        Calendar cl = Calendar.getInstance();
        cl.setTime(new Date());
        cl.add(Calendar.DATE, 2);
        cl.set(Calendar.HOUR_OF_DAY, 23);
        cl.set(Calendar.MINUTE, 59);
        cl.set(Calendar.SECOND, 59);

        Date date = new Date();
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);

        System.out.println(cl.getTime().toLocaleString());
        System.out.println(date.toLocaleString());
    }

    @Test
    public void test03() throws Exception {
        String s = null;
        System.out.println(StringUtils.isNotEmpty(s));// false
        s = " ";
        System.out.println(StringUtils.isNotEmpty(s));// true
        System.out.println(StringUtils.isNotEmpty(""));// false
        s = null;
        System.out.println(StringUtils.isNotEmpty(s + ""));// true

        System.out.println(StringUtils.isNotBlank(s + ""));// true
        System.out.println(StringUtils.isBlank(s + ""));// false
        System.out.println(StringUtils.isEmpty(s + ""));// false

        System.out.println(null + "");// null
    }
}
