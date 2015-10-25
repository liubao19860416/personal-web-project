package socket.download;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程实现文件下载服务端程序
 * 
 * @author Liubao
 * @Date 2014年7月18日
 * @Version 1.0
 */
public class TCPMultiThreadDownloadServer {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        // 多线程服务器；
        System.out.println("多线程文件下载,服务器端程序启动了……");
        ServerSocket serverSocket = new ServerSocket(20001);
        while (true) {
            // 获取客户端连接进来的的socket对象
            Socket socket = serverSocket.accept();
            // 实现文件下载的线程类
            new Thread(new TCPDownloadServer(socket)).start();
        }
    }
}