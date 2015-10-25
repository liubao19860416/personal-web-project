package math;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

//使用Base64算法对字符串进行初步编码;生成16进制的8位字符串;
@SuppressWarnings("restriction")
public class Base64EncoderUtil {
    private static BASE64Encoder base64Encoder = new BASE64Encoder();
    private static BASE64Decoder base64Decoder = new BASE64Decoder();

    public static String base64Encoder(String str) {
        String encodestr = null;
        try {
            encodestr = base64Encoder.encode(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("您使用的编码格式不正确!");
        }

        // 在这里还可以再进一步使用MD5算法进行加密;
        // encodestr = MD5Util.md5(encode);

        return encodestr;
    }

    public static String base64Decoder(String str) {
        byte[] strBytes = null;
        String decode = null;
        try {
            strBytes = base64Decoder.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("BASE64Decoder解码出现异常!!");
        }

        try {
            // 如果数据使用了MD5算法进行加密;是不能够被解密的!!!
            decode = new String(strBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("您使用的编码格式不正确!");
        }
        return decode;
    }
}
