package uuid;

import java.util.Random;

/**
 * 随机数、随即字符串工具
 */
public class RandomUtils {
    
    public static final String ALLCHARANDNUMBER = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String CHARAONLY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERONLY = "0123456789";

    //测试
    public static void main(String[] args) {
        System.out.println(RandomUtils.generateString(8));
        System.out.println(generateCharaOnlyString(40));
         System.out.println(toFixdLengthString(123, 5));
    }
    
    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHARANDNUMBER.charAt(random.nextInt(ALLCHARANDNUMBER.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     */
    public static String generateCharaOnlyString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARAONLY.charAt(random.nextInt(CHARAONLY.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含小写字母)
     */
    public static String generateLowerCharaOnlyString(int length) {
        return generateCharaOnlyString(length).toLowerCase();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含大写字母)
     */
    public static String generateUpperCharaOnlyString(int length) {
        return generateCharaOnlyString(length).toUpperCase();
    }

    /**
     * 生成一个定长的纯0字符串
     */
    public static String generateZeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }
    
    /**
     * 返回一个定长的随机字符串(只包含数字)
     */
    public static String generateNumberOnlyString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBERONLY.charAt(random.nextInt(NUMBERONLY.length())));
        }
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     */
    public static String toFixdLengthString(long num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
                    + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     */
    public static String toFixdLengthString(int num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = String.valueOf(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
                    + "的字符串发生异常！");
        }
        sb.append(strNum);
        return sb.toString();
    }

}
