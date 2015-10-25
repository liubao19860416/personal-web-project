package socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 * UDP一次发送和接收数据测试
 * @author Liubao
 * @2014年12月5日
 *
 */
public class UDPOnceSenderAndReceiver {

    public static void main(String[] args) {

        new Thread(new UDPReceiver()).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new UDPSender()).start();

        System.out.println("-----main over------");
    }

}

class UDPSender implements Runnable {
    DatagramSocket ds = null;
    DatagramPacket dp = null;

    @Override
    public void run() {
        System.out.println("-----UDPSender  starting------");

        String str = "UDPSender  sending... ";
        byte[] buf = str.getBytes();
        try {
            ds = new DatagramSocket();
            dp = new DatagramPacket(buf, 0, buf.length, InetAddress.getByName("127.0.0.1"), 20000);
            ds.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            ds.close();
        }
        System.out.println("-----UDPSender  closing------");
    }

}

class UDPReceiver implements Runnable {
    DatagramSocket ds = null;
    DatagramPacket dp = null;
    @Override
    public void run() {
        System.out.println("-----UDPReceiver  starting------");
        try {
            byte[] buf = new byte[1024];
            ds = new DatagramSocket(20000);
            dp = new DatagramPacket(buf, 0, buf.length);
            ds.receive(dp);
            String ip = dp.getAddress().getHostAddress();
            int port = dp.getPort();
            String str = new String(dp.getData(), 0, dp.getLength());
            System.out.println("UDPReceiver[IP:" + ip + ",PORT:" + port+ ",STRING:" + str + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            ds.close();
        }
        System.out.println("-----UDPReceiver  closing------");
    }
    
}