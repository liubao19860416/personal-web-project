package socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 数据从键盘录入，进行传输
 * @author Liubao
 * @2014年12月6日
 *
 */
public class TCPServerAndClientBySystemInOut {

    public static void main(String[] args) {
        Socket sc = new Socket();
        new Thread(new Server2(sc)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Client2(sc)).start();
        System.out.println("===============main over==============");
    }
}

class Client2 implements Runnable {
    private Socket sc = null;
    public Client2(Socket sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        System.out.println("===============Client start==============");
        BufferedReader bufr = null;
        BufferedReader bufr_In = null;
        PrintWriter pw_Out = null;
        try {
            sc = new Socket("127.0.0.1", 20004);
            bufr = new BufferedReader(new InputStreamReader(System.in));
            pw_Out = new PrintWriter(sc.getOutputStream(), true);
            bufr_In = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            String line = null;
            while ((line = bufr.readLine()) != null) {
                if (!line.matches("\\w{1,}+")){
                    continue;
                }
                pw_Out.println(line);
                if ("over".equalsIgnoreCase(line)) {
                    break;
                }
                line = bufr_In.readLine();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }finally{
            try {
                if(sc!=null){
                    sc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                sc=null;
            }
        }
        System.out.println("===============Client over==============");
    }
}

class Server2 implements Runnable {
    private Socket sc = null;

    private ServerSocket ss = null;
    private BufferedReader bufr_In = null;
    private PrintWriter pw_Out = null;

    public Server2(Socket sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        System.out.println("===============Server start==============");
        try {
            ss = new ServerSocket(20004);
            sc = ss.accept();
            String ip = sc.getInetAddress().getHostAddress();
            String line = null;
            bufr_In = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            pw_Out = new PrintWriter(sc.getOutputStream(), true);
            while ((line = bufr_In.readLine()) != null) {
                if ("over".equalsIgnoreCase(line)) {
                    break;
                }
                pw_Out.println(line.toUpperCase());
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally{
            try {
                sc.close();
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                sc=null;
                ss=null;
            }
        }

        System.out.println("===============Server over==============");
    }
}
