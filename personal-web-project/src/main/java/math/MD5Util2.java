package math;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util2 {
    /**
     * 使用md5的算法进行加密,生成等长度的32位数据
     */
    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("算法类型输入错误！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
    
    
    //*81F5E21E35407D884A6CD4A731AEBFB6AF209E1B====root
    public static void main(String[] args) {
        System.out.println(MD5Util2.md5("root"));
        System.out.println(MD5Util2.md5("liubao"));
    }

}