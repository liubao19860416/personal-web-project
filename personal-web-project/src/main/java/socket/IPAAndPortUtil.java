package socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class IPAAndPortUtil {
    /**
     * 通过输入的参数， 计算server的端口号
     */
    @SuppressWarnings("unused")
    private static ServerSocket getServerPort(String[] args,ServerSocket server) {
        if (args.length == 1) {
            try {
                server = new ServerSocket(Integer.parseInt(args[0]));
            } catch (Exception e) {
            }
        } else if (args.length == 0) {
            try {
                server = new ServerSocket(20010);
            } catch (IOException e) {
            }
        }
        return server;
    }

    /**
     * 通过配置文件读取socket的ip信息
     * @param client
     * @return
     */
    @SuppressWarnings("unused")
    private static Socket getIPAndPortFromFile(Socket client) {
        File file = new File("d:\\temp\\client_IP.info");
        if (!file.exists()) {
        }
        FileInputStream fis = null;
        BufferedReader bufr0 = null;
        try {
            fis = new FileInputStream(file);
            bufr0 = new BufferedReader(new InputStreamReader(fis, "GBK"));
        } catch (Exception e) {
        }

        String str = null;
        String[] temp = null;
        String[] args0 = new String[2];
        int lineNum = 0;
        boolean flag1 = true;
        boolean flag2 = true;
        try {
            while ((str = bufr0.readLine()) != null) {
                ++lineNum;
                temp = str.split("=");
                switch (temp[0]) {
                case ("IP"):
                    if (flag1) {
                        flag1 = false;
                        args0[0] = temp[1];
                    }
                    break;
                case ("PORT"):
                    if (flag2) {
                        flag2 = false;
                        args0[1] = temp[1];
                    }
                    break;
                default: {
                    break;
                }
                }
            }
            fis.close();
            bufr0.close();
        } catch (IOException e3) {
        }

        if (args0[0] == null || args0[1] == null) {
        } else {
            try {
                client = new Socket(args0[0], Integer.parseInt(args0[1]));
            } catch (Exception e) {
            }
        }
        return client;
    }
}
