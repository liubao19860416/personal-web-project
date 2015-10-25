package system;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 字符数据从输入流读到输出流中
 * 使用System.in和System.out
 * 
 * @author Liubao
 * @2014年12月3日
 *
 */
public class SystemInOutTest {

    public static void main(String[] args) {

        String str = new String("abcdefghijklmnoprstuvwxyz");
        byte[] sur_byte = str.getBytes();

        ByteArrayInputStream in = new ByteArrayInputStream(sur_byte);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // transform(in, out);

        transform(System.in, System.out);

        byte[] result = out.toByteArray();
        System.out.println(new String(result));

    }

    //字符数据从输入流读到输出流中
    private static void transform(InputStream in, OutputStream out) {
        int ch;
        try {
            while ((ch = in.read()) != -1) {
                if (ch != 'q' && ch != 'Q') {
                    out.write(Character.toUpperCase(ch));
                } else {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
