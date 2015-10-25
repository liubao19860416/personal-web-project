package socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
/**
 * UDP多线程实现简单通信：接收端
 * @author Liubao
 * @2014年12月6日
 *
 */
public class UDPReceive1 implements Runnable {

    private DatagramSocket datagramSocket;
    public UDPReceive1(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {

        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        datagramSocket.close();
    }

    private void execute() throws IOException {
        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            datagramSocket.receive(dp);
            String ip = dp.getAddress().getHostAddress();
            int port = dp.getPort();
            byte[] buf2 = dp.getData();
            String str2 = new String(buf2, 0, dp.getLength()); 
            if ("over".equals(str2)) {
                break;
            } else {
                System.out.println(ip + "::" +port+"："+ str2);
            }
        }

    }

}
