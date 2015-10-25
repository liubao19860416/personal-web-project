package socket.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

/**
 * 文件下载服务端
 * 
 * @author Liubao
 * @2014年12月6日
 * 
 */
public class TCPDownloadServer implements Runnable {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(20011);
        while (true) {
            Socket s = ss.accept();
            new Thread(new TCPDownloadServer(s)).start();

        }
    }

    private Socket s;
    public TCPDownloadServer(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        String ip = s.getInetAddress().getHostAddress();
        try {
            File sourFile = new File("D:\\temp\\��ѻ�.mp3");
            BufferedInputStream bufInStream = new BufferedInputStream(new FileInputStream(sourFile));
            BufferedOutputStream bufOutStream = new BufferedOutputStream(s.getOutputStream());
            BufferedReader bufr = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter bufOut = new BufferedWriter(new FileWriter("D:\\temp\\Records.records", true));// ׷�ӷ�ʽд����ݡ�
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = bufInStream.read(buf)) != -1) {
                bufOutStream.write(buf, 0, len);
                bufOutStream.flush();
            }
            s.shutdownOutput(); 
            String line2 = bufr.readLine();
            System.out.println("下载结果:" + line2);
            bufOut.write("IP[" + ip + "]"+ (DateFormat.getDateTimeInstance().format(new Date()))
                    + "===>>>" + sourFile.getAbsolutePath() + "!!!\r\n");
            bufOut.flush();
            
            // bufOutStream.close();
            bufInStream.close();
            bufOut.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
