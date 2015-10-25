package socket.udp;

import java.net.DatagramSocket;
import java.net.SocketException;

import org.junit.Test;

public class UDPTestController {

    @Test
	public void test01() throws SocketException {
		DatagramSocket sendSocket=new DatagramSocket();
		DatagramSocket receSocket=new DatagramSocket(20001);
		
		//测试方法1
		new Thread(new UDPSender1(sendSocket)).start();
		new Thread(new UDPReceive1(receSocket)).start();
		
	}
    
    @Test
    public void test02() throws SocketException {
        DatagramSocket sendSocket=new DatagramSocket();
        DatagramSocket receSocket=new DatagramSocket(20002);
        
        //测试方法2
        new Thread(new UDPSender2(sendSocket)).start();
        new Thread(new UDPReceive2(receSocket)).start();
    }

}
