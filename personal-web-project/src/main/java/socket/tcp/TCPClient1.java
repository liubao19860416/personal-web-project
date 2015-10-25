package socket.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * 从键盘读取输入，然后将该数据转换为大写字母，发送给服务端；同时将读取到的服务端的返回值输出；
 * @author Liubao
 * @2014年12月6日
 *
 */
public class TCPClient1 {
	public static void main(String[] args) throws UnknownHostException,IOException {
		System.out.println("TCPClient3.starting");
		
		Socket sc = new Socket("127.0.0.1", 20008);

		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
		
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(sc.getInputStream()));
		
		while (true) {
			String line = bufr.readLine();
			bufOut.write(line.toUpperCase() + "\n");
			bufOut.flush();
			 if ("".equals(line)) {
                 continue;
             }
             if ("\r".equals(line)) {
                 continue;
             }
             if ("over".equalsIgnoreCase(line)) {
                 break;
             }
			String line2 = bufIn.readLine();
			System.out.println("服务端的返回值："+line2);
		}
		System.out.println("tcp_client.ending");
		bufOut.close();
		bufIn.close();
		bufr.close();
		sc.close();
	}
}
