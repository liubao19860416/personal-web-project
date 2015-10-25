package socket.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 文件上传服务器，多线程实现
 * @author Liubao
 * @2014年12月6日
 *
 */
public class TCPUploadFileServer implements Runnable {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(20011);
        while (true) {
            Socket s = ss.accept();
            new Thread(new TCPUploadFileServer(s)).start();
        }
    }

    private Socket s;
    public TCPUploadFileServer(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        String ip = s.getInetAddress().getHostAddress();
        File destFile = CreatFileName(ip);
        try {
            BufferedInputStream bufInStream = new BufferedInputStream(s.getInputStream());
            BufferedOutputStream bufOutStream = new BufferedOutputStream(new FileOutputStream(destFile));
            BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = bufInStream.read(buf)) != -1) {
                bufOutStream.write(buf, 0, len);
            }

            bufw.write("上传成功。。。");
            bufw.flush();

            bufOutStream.close();
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建目的文件方法
     */
    private static File CreatFileName(String ip) {
        File dir = new File("d:\\temp\\");
        int count = 0;
        if (!dir.exists()) {
            dir.mkdir();
        }
        File destFile = new File(dir, ip + ".mp3");
        while (destFile.exists()) {
            destFile = new File(dir, ip + "(" + (count++) + ".mp3");
        }
        return destFile;

    }
}
