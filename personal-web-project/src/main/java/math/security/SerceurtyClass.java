package math.security;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

/**
 * 一个简单的文件字节流加密工具; 该程序既是加密,也是解密程序;
 * 在这里指定加密规则;因为要加密,所以使用字符传递;但是可以在外面加上包装的缓冲流来提高效率;
 */
public class SerceurtyClass {
    public static void cypher(InputStream in, OutputStream out) {
        int len = 0;
        // byte[] buf = new byte[1024];
        try {
            while ((len = in.read()) != -1) {
                // 加密规则和解密规则一样!!!
                out.write(len ^ 0xff);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("数据读写加密失败!!!");
        }
    }

    @Test
    public void test() throws Exception {
        // 加密顺序
        String source1 = "d://1.java";
        String dest1 = "d://encode_Sercet_1.java";

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(source1));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest1));
        
        // 加密操作;
        SerceurtyClass.cypher(in, out);

        // 解密顺序
        String source2 = "d://encode_Sercet_1.java";
        String dest2 = "d://decode_Secret_1.java";
        
        BufferedInputStream in2 = new BufferedInputStream(new FileInputStream(source2));
        BufferedOutputStream out2 = new BufferedOutputStream(new FileOutputStream(dest2));
        
        // 解密操作
        SerceurtyClass.cypher(in2, out2);
    }

}
