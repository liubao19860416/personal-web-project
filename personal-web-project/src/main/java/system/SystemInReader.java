package system;

import java.io.IOException;
/**
 * 简单的对输入文字信息进行输出操作
 * @author Liubao
 * @2014年12月6日
 *
 */
public class SystemInReader {

    public static void main(String[] args) {
        String line = null;
        byte[] buf = new byte[100];
        while (true) {
            buf = new byte[100];
            try {
                System.in.read(buf);
            } catch (IOException e) {
                e.printStackTrace();
            }
            line = new String(buf, 0, buf.length).trim();
            if ("over".equalsIgnoreCase(line)) {
                break;
            }
            System.out.println(line);
        }
    }

}
