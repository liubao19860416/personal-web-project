package socket.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
/**
 * 文件下载客户端
 * @author Liubao
 * @2014年12月6日
 *
 */
public class TCPDownloadClient {

	public static void main(String[] args) throws IOException {
		Socket sc = new Socket("127.0.0.1", 20011);
		String ip = sc.getLocalAddress().getHostAddress();
		BufferedInputStream bufInStream = new BufferedInputStream(sc.getInputStream());
		File destFile = creatDestFile(ip);
		BufferedOutputStream bufOutStream = new BufferedOutputStream(new FileOutputStream(destFile));
		BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(sc.getOutputStream()));
		int len = 0;
		byte[] buf = new byte[1024]; 
		while ((len = bufInStream.read(buf)) != -1) {
			bufOutStream.write(buf, 0, len);
			bufOutStream.flush(); 
		}

		bufOut.write(" !!!!");
		bufOut.flush();

		bufOutStream.close();
		bufOut.close();
		bufInStream.close();
		sc.close();
	}

	private static File creatDestFile(String ip) {
		File dir = new File("d:\\temp");
		int count = 0;
		if (!dir.exists()) {
			dir.mkdir();
		}
		File destFile = new File(dir, ip + ".mp3");
		while (destFile.exists()) {
			destFile = new File(dir, ip + "(" + (count++) + ").mp3");
		}
		return destFile;
	}

}
