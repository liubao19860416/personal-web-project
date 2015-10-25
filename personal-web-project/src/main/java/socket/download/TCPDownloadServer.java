package socket.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

/**
 * 文件下载服务核心实现代码(多线程实现)
 * 
 * @author Liubao
 * @Date 2014年7月18日
 * @Version 1.0
 * 
 */
public class TCPDownloadServer implements Runnable {

	private Socket socket;
	public TCPDownloadServer(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// 获取客户端IP地址
		String ip = socket.getInetAddress().getHostAddress();
		System.out.println(ip + ":的客户端连接到了--多线文件下载程服务器…………");
		try {
			// 源1 :读取服务器本地文件信息
			File sourFile = new File("D:\\temp\\001.mp3");
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					new FileInputStream(sourFile));
			// 目的1:输出读取的文件信息,返回给客户端下载使用
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					socket.getOutputStream());
			// 开始进行数据的读取
			byte[] buf = new byte[1024];
			int len = -1;
			while ((len = bufferedInputStream.read(buf)) != -1) {
				bufferedOutputStream.write(buf, 0, len);
				bufferedOutputStream.flush();
			}
			// 给客户端反馈的下载结果:下载成功
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream()));
			bufferedWriter.newLine();
			bufferedWriter.write("下载文件成功!!!");
			bufferedWriter.newLine();
			bufferedWriter.flush();
			// 能够会发送结束标记信号呢?
			socket.shutdownOutput();
			// 目的2:保存下载客户下载数据的记录,到服务器端的本地文件
			bufferedWriter = new BufferedWriter(new FileWriter(
					"D:\\temp\\download\\download.txt", true));// 采用追加方式写入数据到文件。
			// 加入一个客户下载记录清单, 上面记录了下载客户的IP，下载时间和相应的下载的文件名称；
			bufferedWriter.write("IP地址[" + ip + "]的客户端在时间<"
					+ (DateFormat.getDateTimeInstance().format(new Date()))
					+ ">下载了文件：" + sourFile.getAbsolutePath() + "!!!\r\n");
			bufferedWriter.flush();
			// 释放资源
			// bufferedOutputStream.close();
			bufferedInputStream.close();
			bufferedWriter.close();
			// socket.close();
			System.out.println(ip + "：的客户端离开了--多线程服务器端………………");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
