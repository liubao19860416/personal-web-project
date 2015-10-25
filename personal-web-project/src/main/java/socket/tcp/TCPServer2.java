package socket.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 服务端将客户端发送的消息，写入到指定的文件中
 * @author Liubao
 * @2014年12月6日
 *
 */
public class TCPServer2 {
    public static void main(String[] args) throws IOException {
        System.out.println("tcp_server.starting");
        ServerSocket ss = new ServerSocket(20009);
        while (true) {
            Socket s = ss.accept(); 
            String ip = s.getInetAddress().getHostAddress();

            BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            BufferedWriter bufOut = new BufferedWriter(new FileWriter("d:\\temp\\001_0.txt"));
            String line = null;
            while ((line = bufIn.readLine()) != null) {
                bufOut.write(line + "\r\n"); 
                bufOut.flush();
            }
            out.write( "\n");
            out.flush();

            bufIn.close();
            bufOut.close();
            // ss.close();
        }

    }
}