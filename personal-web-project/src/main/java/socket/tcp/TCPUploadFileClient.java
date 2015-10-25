package socket.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
/**
 * 文件上传客户端，多线程实现
 * @author Liubao
 * @2014年12月6日
 *
 */
public class TCPUploadFileClient {

    public static void main(String[] args) throws IOException {
        Socket sc = new Socket("192.168.1.148", 20011);
        BufferedInputStream bufInStream = new BufferedInputStream(new FileInputStream("d:\\temp\\000.mp3"));
        BufferedOutputStream bufOutStream = new BufferedOutputStream(sc.getOutputStream());
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = bufInStream.read(buf)) != -1) {
            bufOutStream.write(buf, 0, len);
            bufOutStream.flush();
        }

        //关闭连接
        sc.shutdownOutput();

        String line2 = bufIn.readLine();
        System.out.println("读取上传结果：" + line2);

        bufOutStream.close();
        bufIn.close();
        bufInStream.close();

        sc.close();
    }

}
