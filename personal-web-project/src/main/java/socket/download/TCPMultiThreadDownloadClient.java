package socket.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 多线程-下载文件客户端
 * 
 * @author Liubao
 * @Date 2014年7月18日
 * @Version 1.0
 */
public class TCPMultiThreadDownloadClient {

    public static void main(String[] args) throws IOException {
        System.out.println("多线程下载程序-客户端启动了*************");
        // 创建一个客户端socket
        Socket socket = new Socket("192.168.1.202", 20001);
        // 获取客户端IP地址信息
        String IPAddr = socket.getLocalAddress().getHostAddress();
        // 源1：读取服务器端返回的需要下载文件的字节流；
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                socket.getInputStream());
        // 目的1：将读取到的文件,输出到本地硬盘文件存储；
        File destFile = creatDestFile(IPAddr);// 工具类(根据ip地址,生成目的文件路径信息)
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream(destFile));
        // 进行数据的发送和接收
        int len = -1;
        byte[] buf = new byte[1024 * 1024];// 定义了一个1MB大小的缓冲区，每次传送1kb大小的字节数据内容；
        while ((len = bufferedInputStream.read(buf)) != -1) {
            bufferedOutputStream.write(buf, 0, len);
            bufferedOutputStream.flush();// 如果不刷新数据的话，容易丢失一个不大于缓冲区大小的数据内容；
        }
        // 关闭socket输入流,自动发送结束标记
        // socket.shutdownInput();//关闭后就不能再被使用了
        // 源2：socket输入流,输出下载数据完成文字信息。(更改使用DataInputStream实现读取字符流)
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        // 下载完成之后,发送下载成功文字信息
        String readLine = bufferedReader.readLine();
        System.out.println("读取到的服务端返回的下载结果:" + readLine);
        // 释放IO资源
        bufferedInputStream.close();
        bufferedOutputStream.close();
        bufferedReader.close();
        // 客户端关闭自己的socket
        socket.close();
        System.out.println("多线程下载程序-客户端结束了*************");
    }

    /**
     * 创建目的文件的方法
     */
    private static File creatDestFile(String ip) {
        File dir = new File("d:\\temp\\download");// 本地下载文件存储目录
        int count = 0;
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 指定下载文件的格式为.mp3格式
        File destFile = new File(dir, ip + ".mp3");
        while (destFile.exists()) {
            destFile = new File(dir, ip + "(" + (count++) + ").mp3");
        }
        return destFile;
    }

}
