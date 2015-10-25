package gui.chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 简易聊天程序服务端代码
 * 
 * @author Liubao
 * @2014年10月31日
 * 
 */
public class VerySimpleChatServer {
    ArrayList<PrintWriter> clientOutputStream;
    private ServerSocket serverSocket;
    
    //服务端程序主方法入口
    public static void main(String[] args) {
        System.out.println("服务端程序启动了。。。");
        new VerySimpleChatServer().go();
        System.out.println("服务端程序关闭了。。。");
    }
    
  //服务端程序的方法如入口
    private void go() {
        clientOutputStream = new ArrayList<PrintWriter>();
        try {
            serverSocket = new ServerSocket(5000);
            while (true) {
                //获取当前请求的客户端socket对象
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(
                        clientSocket.getOutputStream());
                clientOutputStream.add(writer);

                //启动线程，接受客户端请求等
                new Thread(new ClientHandler(clientSocket)).start();
                System.out.println("获得一个客户端连接："+clientSocket.getInetAddress().getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //服务端需要执行的线程的Task任务
    public class ClientHandler implements Runnable {
        BufferedReader reader;
        Socket socket;

        //将客户端发送过来字节流的数据，包装成字符流，放到指定的流对象中
        public ClientHandler(Socket clientSocket) {
            try {
                this.socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(
                        socket.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //读取客户端发来的信息并广播给其他在线的客户端
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("服务端接收到的数据为:" + message);
                    //广播给其他在线客户端用户的方法
                    tellEveryOne(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //广播给其他在线客户端用户的方法
        private void tellEveryOne(String message) {
            Iterator<PrintWriter> it = clientOutputStream.iterator();
            while (it.hasNext()) {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();
            }
        }

    }

    

}
