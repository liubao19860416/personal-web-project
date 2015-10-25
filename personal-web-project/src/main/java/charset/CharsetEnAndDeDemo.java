package charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * 字符编码和解码（ISO-8859-1不支持中文格式的编码，会抛出异常！！）
 * 
 * @author Liubao
 * @2015年4月5日
 * 
 */
public class CharsetEnAndDeDemo {
    
    public static void main(String args[]) throws Exception {
        // 只能表示的英文字符 UTF-8
        Charset latin1 = Charset.forName("ISO-8859-1"); 
        CharsetEncoder encoder = latin1.newEncoder(); // 得到编码器
        CharsetDecoder decoder = latin1.newDecoder(); // 得到解码器
        // 通过CharBuffer类中的
        // CharBuffer cb = CharBuffer.wrap("北京MLDN软件实训中心！") ;
        CharBuffer cb = CharBuffer.wrap("LIUBAO");
        ByteBuffer buf = encoder.encode(cb); // 进行编码操作
        System.out.println(decoder.decode(buf)); // 进行解码操作
        
        //支持中文格式
        latin1 = Charset.forName("UTF-8"); 
        encoder = latin1.newEncoder(); // 得到编码器
        decoder = latin1.newDecoder(); // 得到解码器
        cb = CharBuffer.wrap("北京MLDN软件实训中心！") ;
        buf = encoder.encode(cb); // 进行编码操作
        System.out.println(decoder.decode(buf)); // 进行解码操作
    }
}