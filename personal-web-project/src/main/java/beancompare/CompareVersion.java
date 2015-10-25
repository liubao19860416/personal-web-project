package beancompare;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 简单版本号比较
 * 
 * @author Liubao
 * @2015年3月6日
 * 
 */
public class CompareVersion {

    public static void main(String[] args) {
        String str1="9.0.0.0";
        String str2="10.1.2.0";
        System.out.println(value(str1));
        System.out.println(value(str2));
        System.out.println(value("10.1.2.0") > value("9.0.0.0"));//true
        System.out.println(str1.compareTo(str2)>0);//true,这里的这种直接判断是有问题的
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        
        int i = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        System.out.println(i);
        System.out.println(Calendar.HOUR_OF_DAY);
    }

    public static long value(final String object) {
        if (object.contains(".")) {
            final int index = object.lastIndexOf(".");
            return value(object.substring(0, index)) * 100
                    + value(object.substring(index + 1));
        } else
            return Long.valueOf(object);
    }
}