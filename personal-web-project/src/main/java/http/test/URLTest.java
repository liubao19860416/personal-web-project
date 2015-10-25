package http.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {
    public static void main(String[] args) throws IOException {

        String str = "http://www.baidu.com";
        URL url = new URL(str);

        System.out.println(url.getContent());//
        System.out.println("getHost:" + url.getHost());
        System.out.println("getPort:" + url.getPort());
        System.out.println("getProtocol:" + url.getProtocol());
        System.out.println("getFile:" + url.getFile());
        System.out.println("getPath:" + url.getPath());
        System.out.println("getQuery:" + url.getQuery());
        System.out.println("getUserInfo:" + url.getUserInfo());

        InputStream in = url.openStream();
        URLConnection connection = url.openConnection();
        InputStream in2 = connection.getInputStream();

        byte[] buf = new byte[1024];
        int len = 0;
        String temp = null;

        while ((len = in.read(buf)) != -1) {
            temp = new String(buf, 0, len, "utf-8");
            System.out.println(temp);
        }

        in.close();

    }

}
