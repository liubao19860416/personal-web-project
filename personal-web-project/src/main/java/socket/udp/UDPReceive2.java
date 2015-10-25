package socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceive2 implements Runnable {

    private DatagramSocket receSocket;

    public UDPReceive2(DatagramSocket receSocket) {
        this.receSocket = receSocket;
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        receSocket.close();

    }

    private void execute() throws IOException {
        byte[] buf = new byte[1024];
        while (true) {
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            receSocket.receive(dp);
            String ip = dp.getAddress().getHostAddress();
            int port = dp.getPort();
            byte[] buf2 = dp.getData();
            String str = new String(buf2, 0, dp.getLength()); 
            if (str.equals("over")) {
                break;
            } else {
                System.out.println(ip + "::" + str);
            }
        }

    }
}
