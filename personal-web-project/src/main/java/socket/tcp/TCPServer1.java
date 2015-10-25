package socket.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 读取客户端发送过来的字符，并将其转换为小写字母返回
 * @author Liubao
 * @2014年12月6日
 *
 */
public class TCPServer1 {
    public static void main(String[] args) throws IOException {
        System.out.println("TCPServer3.starting");

        ServerSocket ss = new ServerSocket(20008);

        while (true) {
            Socket s = ss.accept();

            String ip = s.getInetAddress().getHostAddress();

            BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            String line = null;
            while ((line = bufIn.readLine()) != null) {
                if ("".equals(line)) {
                    continue;
                }
                if ("\r".equals(line)) {
                    continue;
                }
                if ("over".equalsIgnoreCase(line)) {
                    break;
                }
                bufOut.write(line.toLowerCase() + "\n");
                bufOut.flush();
            }
            // bufIn.close();
            // bufOut.close();
            // ss.close();
        }

    }
}