package socket.udp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 通过键盘输入相应数据，或者读取指定文件中的数据，进行传输
 * System.in和System.out
 * 结束标记：886
 * 输出转换为大写字母
 * 
 * @author Liubao
 * @2014年12月5日
 *
 */
public class UDPBySystemInOutSenderAndReceiver {

    public static void main(String[] args) {

        DatagramSocket ds = null;

        new Thread(new UDPReceiver3(ds)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        new Thread(new UDPSender1(ds)).start();

        System.out.println("-----main over------");
    }

}

class UDPSender3 implements Runnable {

    private DatagramSocket ds = null;

    public UDPSender3(DatagramSocket ds) {
        this.ds = ds;
    }

    DatagramPacket dp = null;

    @Override
    public void run() {
        System.out.println("-----UDPSender1  starting------");
        // try {
        // System.setIn(new FileInputStream("d:\\temp\\source_UDP.info"));
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        byte[] buf = new byte[1024];
        String line = null;
        try {
            ds = new DatagramSocket();
            while ((line = bufr.readLine()) != null) {
                buf = line.getBytes();
                dp = new DatagramPacket(buf, 0, buf.length,InetAddress.getByName("127.0.0.1"), 20002);
                ds.send(dp);
                if ("over".equalsIgnoreCase(line)) {
                    break;
                }
            }
            System.out.println("UDPSender1:"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            System.out.println("-----UDPSender1  closing------\r\n");
        } catch (Exception e1) {
            e1.printStackTrace();
        }finally{
            // ds.close();
        }

    }

}

class UDPReceiver3 implements Runnable {
    private DatagramSocket ds = null;
    public UDPReceiver3(DatagramSocket ds) {
        this.ds = ds;
    }
    DatagramPacket dp = null;

    @Override
    public void run() {
        // try {
        // System.setOut(new PrintStream(new
        // FileOutputStream("d:\\temp\\dest_UDP.info",true),true));
        // } catch (FileNotFoundException e2) {
        // e2.printStackTrace();
        // }
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));
        String temp = null;
        System.out.println("-----UDPReceiver1  starting------");
        try {
            ds = new DatagramSocket(20002);
            while (true) {
                byte[] buf = new byte[1024];
                dp = new DatagramPacket(buf, 0, buf.length);
                ds.receive(dp);
                String ip = dp.getAddress().getHostAddress();
                int port = dp.getPort();
                String str = new String(dp.getData(), 0, dp.getLength());
                temp = "UDPReceiver1[IP:" + ip + ",PORT:" + port + ",STRING:" + str.toUpperCase() + "]";
                //进行读写
                bufw.write(temp);
                bufw.newLine();
                bufw.flush();
                if ("886".equalsIgnoreCase(str)) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufw != null) {
                    bufw.close();
                }
                // ds.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                bufw = null;
            }
        }
        System.out.println("UDPReceiver1:"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println("-----UDPReceiver1  closing------\r\n");

    }

}