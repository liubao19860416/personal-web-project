package socket.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * 将读取的指定文件信息发送给服务端；并打印服务端返回的结果；
 * 
 * @author Liubao
 * @2014年12月6日
 *
 */
public class TCPClient2 {
    public static void main(String[] args) throws UnknownHostException,IOException {
        System.out.println("tcp_client.starting");

        Socket sc = new Socket("192.168.1.148", 20009);

        // BufferedInputStream bufIN=new BufferedInputStream(new FileInputStream("d:\\temp\\001.java"));
        BufferedReader bufr = new BufferedReader(new FileReader( "D:\\temp\\001.java"));
        BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));

        BufferedReader bufIn = new BufferedReader(new InputStreamReader(sc.getInputStream()));

        String line = null;
        while ((line = bufr.readLine()) != null) {
            bufOut.write(line + "\n");
            bufOut.flush();
        }

        //写入结束标记
         bufOut.write("over"+"\n");
         bufOut.flush();

         //关闭连接
        sc.shutdownOutput();

        String line2 = bufIn.readLine();
        System.out.println("反馈结果：" + line2);

        System.out.println("tcp_client.ending");
        bufOut.close();
        bufIn.close();
        bufr.close();
        sc.close();
    }
}
