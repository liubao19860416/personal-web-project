package socket.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

/**
 * 上传文件的服务端实现代码(多线程)
 * 
 * @author Liubao
 * @Date 2014年7月18日
 * @Version 1.0
 * 
 */
public class TCPUploadServer implements Runnable {
    
    private Socket socket;
    public TCPUploadServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String ipAddr = socket.getInetAddress().getHostAddress();
        System.out.println(ipAddr + ":的客户端连接到了上传文件服务器…………");
        // 生成保存上传文件的目录地址等信息
        File destFile = creatFileName(ipAddr);
        try {
            // 读取客户端发送的上传文件socket对象中的文件信息
            BufferedInputStream bufferedInputStream = new BufferedInputStream(
                    socket.getInputStream());
            // 保存数据到服务端指定位置destFile存储
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream(destFile));
            // 开始读取文件
            byte[] buf = new byte[1024 * 1024];
            int len = -1;
            while ((len = bufferedInputStream.read(buf)) != -1) {
                bufferedOutputStream.write(buf, 0, len);
                bufferedOutputStream.flush();
            }
            // 这里必须手动关闭socket流,否则会出现等待阻塞情况
            socket.shutdownInput();
            // 反馈给客户端上传成功文字信息
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            // 反馈上传成功字样(一定要指定结束标记)
            bufferedWriter.write("文件已经上传成功！！！");
            // 一定要指定结束标记,这一行不能少!!!
            bufferedWriter.newLine();
            bufferedWriter.flush();
            // 记录日志信息
            // 加入一个客户下载记录清单, 上面记录了下载客户的IP，下载时间和相应的下载的文件名称；
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(
                            "D:\\temp\\upload\\upload.log"), true)));
            bufferedWriter.write("IP地址[" + ipAddr + "]的客户端在时间<"
                    + (DateFormat.getDateTimeInstance().format(new Date()))
                    + ">上传了文件：" + destFile.getAbsolutePath() + "!!!\r\n");
            bufferedWriter.flush();
            // 释放资源
            bufferedOutputStream.close();
            bufferedWriter.close();
            // bufferedInputStream.close();
            // socket.close();
            System.out.println(ipAddr + "：的客户端离开了服务器端………………");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建保存文件到服务端制定目录的方法
     */
    public static File creatFileName(String ipAddr) {
        File dir = new File("d:\\temp\\upload\\");// 文件上传保存目录
        int count = 0;
        if (!dir.exists()) {
            dir.mkdir();
        }
        File destFile = new File(dir, ipAddr + ".mp3");// 文件后缀名及其他相关信息
        while (destFile.exists()) {
            destFile = new File(dir, ipAddr + "(" + (count++) + ".mp3");
        }
        return destFile;

    }
}
