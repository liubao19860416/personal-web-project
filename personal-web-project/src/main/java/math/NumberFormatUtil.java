package math;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式化为字符串工具类,主要针对带货币单位的格式化操作
 * 
 * @author Liubao
 * @2014年12月2日
 * 
 */
public class NumberFormatUtil {
    
    // 判断数字的正则表达式
    private static final String NUMBER_PATTERN = "^[0-9]*$";
    private static NumberFormat numberFormat ;
    private static Locale locale =Locale.CHINA;
    
    static{
        numberFormat = NumberFormat.getCurrencyInstance(locale);
    }
    
    //测试
    public static void main(String[] args) {
        double num1 = 30215.00;
        double num2 = 365.0 / 3.0;
        
        System.out.println(NumberFormatUtil.getDouble(num1));
        System.out.println(NumberFormatUtil.getDouble(num1,3));
        System.out.println(NumberFormatUtil.getDouble(num1,Locale.CHINA));
        System.out.println(NumberFormatUtil.getDouble(num2,Locale.US));
        System.out.println(NumberFormatUtil.getDouble(num2,Locale.US,5));
    }
    

    /**
     * 返回指定格式的货币字符串
     */
    public static String getDouble(double num) {
        return numberFormat.format(num);
    }
    
    /**
     * 返回指定格式的字符串
     */
    public static String getDouble(double num, Locale locale) {
        numberFormat=NumberFormat.getCurrencyInstance(locale);
        return numberFormat.format(num);
    }

    /**
     * 可以设置小数点位数
     */
    public static String getDouble(double num, int digits) {
        //设置小数点位数
        numberFormat.setMaximumFractionDigits(digits);
        return numberFormat.format(num);
    }
    
    /**
     * 可以设置小数点位数
     */
    public static String getDouble(double num, Locale locale,int digits) {
        numberFormat = NumberFormat.getIntegerInstance(locale);
        //设置小数点位数
        numberFormat.setMaximumFractionDigits(digits);
        return numberFormat.format(num);
    }
    /**
     * 判断是否是Integer数据
     */
    public static boolean isInteger(String value) {
        Pattern p = Pattern.compile(NUMBER_PATTERN);
        Matcher m = p.matcher(value);
        return m.find();
    }
    /**
     * 判断是否是Flost数据
     */
    public static boolean isFlost(String value) {
        try {
            Float.parseFloat(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}