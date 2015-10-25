package socket.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 多线程-上传文件客户端
 * 
 * @author Liubao
 * @Date 2014年7月18日
 * @Version 1.0
 * 
 */
public class TCPMultiThreadUploadClient {

    public static void main(String[] args) throws IOException {
        System.out.println("多线程上传程序-客户端启动了*************");
        // 创建一个上传客户端socket对象
        // Socket socket = new Socket("Liubao", 20002);
        // Socket socket = new Socket("localhost", 20002);
        // Socket socket = new Socket("127.0.0.1", 20002);
        Socket socket = new Socket("192.168.1.202", 20002);
        // 读取客户端需要上传的文件信息
        String filePath = "D:\\temp\\001.mp3";
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(filePath));
        // 将读取的文件信息发送给服务端socket
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                socket.getOutputStream());
        // 开始进行读取文件
        int len = -1;
        byte[] buf = new byte[1024 * 1024];
        while ((len = bufferedInputStream.read(buf)) != -1) {
            bufferedOutputStream.write(buf, 0, len);
            bufferedOutputStream.flush();
        }
        // 这里必须手动关闭socket流,否则会出现等待阻塞情况
        socket.shutdownOutput();
        // 读取服务端返回的上传成功文字信息
        // DataInputStream dataInputStream = new DataInputStream(
        // socket.getInputStream());
        // String result = dataInputStream.readUTF();
        // 等价实现
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        // 这也是一个阻塞式方法, 阻塞的位置
        String readLine = bufferedReader.readLine();
        System.out.println("读取到的服务端返回的上传结果:" + readLine);

        // 释放IO资源
        // bufferedOutputStream.close();
        bufferedInputStream.close();
        // bufferedReader.close();
        // dataInputStream.close();
        // 客户端关闭自己的socket
        socket.close();
        System.out.println("多线程上传程序-客户端结束了*************");
    }

    /**
     * 读取本地文件的方法
     */
    @SuppressWarnings("unused")
    private static File getSourceFile(String filePath) {
        // 读取需要上传的文件
        File sourceFile = new File(filePath);
        // 获取上传文件后缀名信息
        String surfix = filePath.substring(filePath.lastIndexOf("."));
        return sourceFile;
    }

}
