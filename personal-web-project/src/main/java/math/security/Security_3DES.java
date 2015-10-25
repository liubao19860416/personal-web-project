package math.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;

/**
 * Vcom_3DES加密程序工具，是三重数据加密算法 Des的密钥是8个字节，但实际上只有7个用上，也就是56位。
 * 3des是用3个或2个des密钥加密一串明文，最少112位最多168位。也就是14~21个字母或数字符号。
 * 从安全性上来说密钥位数不足是不能加密的，但有些软件为了保证用户可用，会自动使用某种策略自动填充满，一般是重复填充或采用特定字符，
 * 如果你只填了1234作为密钥，有可能真正用于加密的密钥是123412341234123412341或者123400000000000000000类似的。
 * 另外请注意，最好去做3des的密钥位数不是7或8，因为des的加密解密是同一个过程，这样搞在填充后实际上是只使用了一次des加密
 * 还不如5位6位好••••••
 * 
 * @author Liubao
 * @2014年12月10日
 * 
 */
public class Security_3DES {

    // 密钥存放文件位置
    private final static String keyStrFileName = "3des.key"; 
    // 使用 DEFAULT_IVPARAMETER 中的字节作为 IV 来构造一个 IvParameterSpec 对象。
    private final static String DEFAULT_IVPARAMETER = "1234567890"; 
    /**
     * 1为加密，0为解密
     */
    private int isEncrypt = -1;
    private String keyStr;// 密钥// 加密/解密密钥，长度为16byte或者24byte。
    private String message; // 要加密/解密信息（解密时需为十六进制显示的字符串）

    public static void main(String[] args) {
        //密钥 最多24个字节，最少？
        String key = "yycg12345678901234567890";
        System.out.println("密钥=" + key);
        
        // 待加密串
        String oldstring = "oldstring#" + System.currentTimeMillis();
        System.out.println("待加密串=" + oldstring);
        
        // 进行3des加密，1表示加密，key：密钥
        Security_3DES tempDesEn = new Security_3DES(1, oldstring, key);
        // 加密后的串
        String strTemp = tempDesEn.Vcom3DESChiper();
        System.out.println("加密后：" + strTemp);

        // strTemp为加密后串，0表示解密，key：密钥
        Security_3DES tempDe = new Security_3DES(0, strTemp, key);
        // 解密后的串
        String strTempDe = tempDe.Vcom3DESChiper();
        System.out.println("解密后：" + strTempDe);
    }
    
    
    static{
        
    }

    //实例化对象构造方法
    public Security_3DES(int isEncrypt, String message, String keyStr) {
        this.isEncrypt = isEncrypt;
        this.message = message;
        this.keyStr = keyStr;
    }

    public Security_3DES() {
    }

    /**
     * 加密，解密操作方法
     */
    public String Vcom3DESChiper() {
        //1.读取密钥文件，获取加密密钥key
        InputStream inputStream =null;
        inputStream=Thread.currentThread().getClass().getClassLoader().getResourceAsStream(keyStrFileName);
        inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(keyStrFileName);
        inputStream=this.getClass().getResourceAsStream(keyStrFileName);
        
        if (inputStream==null) {
            System.err.println("Can't Find " + keyStrFileName);
        }

        //如果没有手动输入密钥key，则通过密钥文件进行获取
        if(keyStr==null||"".equalsIgnoreCase(keyStr)){
            BufferedReader in=null;
            try {
                in = new BufferedReader(new InputStreamReader(inputStream));
                String line=null;
                while ((line = in.readLine()) != null) {
                    keyStr+=line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    if(in!=null){
                        in.close();
                    }
                    in=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
         
        //3.使用bouncycastle进行加密
        Security.addProvider(new BouncyCastleProvider());
        //获得密钥
        SecretKey key = new SecretKeySpec(keyStr.getBytes(), "DESede");
        byte[] text = null;
        byte[] bmessage = null;
        String returnStr = null;
        try {
            //获得一个私鈅加密类Cipher，DESede是算法，ECB/CBC是加密模式，PKCS5Padding是填充方式
            //BC？
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding", "BC");
            /**
             * 参数代表含义：使用 DEFAULT_IVPARAMETER 中的字节作为 IV 来构造一个 IvParameterSpec 对象。 
             */
            AlgorithmParameterSpec algorithmparameterspec = new IvParameterSpec(DEFAULT_IVPARAMETER.getBytes());
            if (isEncrypt == 1) {
                //加密操作
                bmessage = message.getBytes("UTF-8");
                cipher.init(Cipher.ENCRYPT_MODE, key, algorithmparameterspec);
                
                //进行BASE64编码
                //BASE64Encoder enc = new BASE64Encoder();
                //bmessage = enc.encode(resultBytes);
                
            } else if (isEncrypt == 0) {
                //解密操作
                bmessage = decodeHex(message);
                cipher.init(Cipher.DECRYPT_MODE, key, algorithmparameterspec);
            } else {
                System.out.println("加解密类型设置错误，请确认输入：1为加密；0为解密");
                return null;
            }
            
            text = cipher.doFinal(bmessage);
            
            if (isEncrypt == 1) {
                returnStr = encodeHex(text);
            } else if (isEncrypt == 0) {
                returnStr = new String(text,"UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStr;
    }

    /**
     * 字节数组byte[]转换为Hex16格式的字符串
     */
    public static final String encodeHex(byte bytes[]) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xff) < 16)
                buf.append("0");
            buf.append(Long.toString(bytes[i] & 0xff, 16));
        }
        return buf.toString();
    }

    /**
     * Hex16进制字符串转解码为字节数组byte[]
     */
    public static final byte[] decodeHex(String hexString) {
        char chars[] = hexString.toCharArray();
        byte bytes[] = new byte[chars.length / 2];
        int byteCount = 0;
        for (int i = 0; i < chars.length; i += 2) {
            int newByte = 0;
            newByte |= hexCharToByte(chars[i]);
            newByte <<= 4;
            newByte |= hexCharToByte(chars[i + 1]);
            bytes[byteCount] = (byte) newByte;
            byteCount++;
        }
        return bytes;
    }

    /**
     * Hex16进制字符转换byte数据
     */
    private static final byte hexCharToByte(char ch) {
        switch (ch) {
        case 48: // '0'
            return 0;

        case 49: // '1'
            return 1;

        case 50: // '2'
            return 2;

        case 51: // '3'
            return 3;

        case 52: // '4'
            return 4;

        case 53: // '5'
            return 5;

        case 54: // '6'
            return 6;

        case 55: // '7'
            return 7;

        case 56: // '8'
            return 8;

        case 57: // '9'
            return 9;

        case 97: // 'a'
            return 10;

        case 98: // 'b'
            return 11;

        case 99: // 'c'
            return 12;

        case 100: // 'd'
            return 13;

        case 101: // 'e'
            return 14;

        case 102: // 'f'
            return 15;

        case 58: // ':'
        case 59: // ';'
        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 63: // '?'
        case 64: // '@'
        case 65: // 'A'
        case 66: // 'B'
        case 67: // 'C'
        case 68: // 'D'
        case 69: // 'E'
        case 70: // 'F'
        case 71: // 'G'
        case 72: // 'H'
        case 73: // 'I'
        case 74: // 'J'
        case 75: // 'K'
        case 76: // 'L'
        case 77: // 'M'
        case 78: // 'N'
        case 79: // 'O'
        case 80: // 'P'
        case 81: // 'Q'
        case 82: // 'R'
        case 83: // 'S'
        case 84: // 'T'
        case 85: // 'U'
        case 86: // 'V'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        case 90: // 'Z'
        case 91: // '['
        case 92: // '\\'
        case 93: // ']'
        case 94: // '^'
        case 95: // '_'
        case 96: // '`'
        default:
            return 0;
        }
    }

    /**
     * set和get方法
     */
    public int getIsEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(int isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
