package socket.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * UDP多线程实现简单通信：发送端
 * @author Liubao
 * @2014年12月6日
 *
 */
public class UDPSender1 implements Runnable {

	private DatagramSocket datagramSocket;
	public UDPSender1(DatagramSocket datagramSocket) {
		this.datagramSocket = datagramSocket;
	}

	@Override
	public void run() {
		BufferedReader bufr = new BufferedReader(new InputStreamReader(
				System.in));
		String line = null;
		try {
			while ((line = bufr.readLine()) != null) {
				byte[] buf = line.getBytes();
				DatagramPacket dp = null;
				try {
					dp = new DatagramPacket(buf, buf.length,
							InetAddress.getByName("192.168.1.255"), 20001);
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
				try {
					datagramSocket.send(dp);
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (line.equals("over")) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		datagramSocket.close();

	}

}
