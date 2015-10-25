package socket.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 服务端将读取的信息写入文件中,线程任务实现
 * @author Liubao
 * @2014年12月6日
 *
 */
public class TCPServerAndClientByRunnable {

    public static void main(String[] args) {

        Socket sc = new Socket();

        new Thread(new Server3(sc)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Client3(sc)).start();

        System.out.println("===============main over==============");
    }
}

class Client3 implements Runnable {
    private Socket sc = null;

    public Client3(Socket sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        System.out.println("===============Client start==============");
        BufferedReader bufr = null;
        BufferedReader bufr_In = null;
        PrintWriter pw_Out = null;
        try {
            //sc = new Socket("localhost", 20005);
            // sc = new Socket("Liubao", 20005);
            sc = new Socket("127.0.0.1", 20004);
            // System.setIn(new FileInputStream("d:\\temp\\0.txt"));
            bufr = new BufferedReader(new InputStreamReader(System.in));
            pw_Out = new PrintWriter(sc.getOutputStream(), true);
            bufr_In = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            String line = null;
            while ((line = bufr.readLine()) != null) {
                if (!line.matches("\\w{1,}+")) 
                {
                    continue;
                }
                if ("over".equalsIgnoreCase(line)) {
                    break;
                }
                pw_Out.println(line);
                line = bufr_In.readLine();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } finally {
            try {
                bufr_In.close();
                bufr.close();
                pw_Out.close();
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("===============Client over==============");
    }
}

class Server3 implements Runnable {
    private Socket sc = null;

    private ServerSocket ss = null;
    private BufferedReader bufr_In = null;
    private PrintWriter pw_Out = null;
    private BufferedWriter pw_Out1 = null;

    public Server3(Socket sc) {
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
            pw_Out1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:\\temp\\TCP.info", true)));
            while ((line = bufr_In.readLine()) != null) {
                // if ("over".equalsIgnoreCase(line)) {
                // break;
                // }
                pw_Out.println(line.toUpperCase());
                pw_Out1.write(line.toUpperCase());
                pw_Out1.newLine();
                pw_Out1.flush();
            }
            pw_Out1.write("时间："+ ip+ new SimpleDateFormat("yyyy-MM-dd [HH:mm:ss]").format(new Date()));
            pw_Out1.newLine();
            pw_Out1.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                pw_Out1.close();
                pw_Out.close();
                bufr_In.close();
                sc.close();
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("===============Server over==============");
    }
}
