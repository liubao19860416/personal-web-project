package socket.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender2 implements Runnable {

    private DatagramSocket sendSocket;

    public UDPSender2(DatagramSocket sendSocket) {
        this.sendSocket = sendSocket;
    }

    @Override
    public void run() {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void execute() throws IOException {
        BufferedReader bufr = new BufferedReader(new InputStreamReader( System.in));
        String line = null;
        while ((line = bufr.readLine()) != null) {
            byte[] buf = line.getBytes();
            DatagramPacket dp = new DatagramPacket(buf, buf.length,
                    InetAddress.getByName("127.0.0.1"), 20002);
            sendSocket.send(dp);
            if (line.equals("over"))
                break;
        }
        sendSocket.close();
    }

}
