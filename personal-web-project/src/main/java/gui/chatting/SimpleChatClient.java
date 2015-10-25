package gui.chatting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * 简易聊天程序客户端程序代码
 * 
 * @author Liubao
 * @2014年10月31日
 * 
 */
public class SimpleChatClient {

    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    // 主方法入口
    public static void main(String[] args) {
        System.out.println("客户端程序启动了。。。");
        new SimpleChatClient().go();
    }

    // 启动聊天窗口的入口方法
    private void go() {
        JFrame frame = new JFrame("client terminal");
        JPanel mainpPnel = new JPanel();
        incoming = new JTextArea(15, 50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(true);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller
                .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller
                .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainpPnel.add(qScroller);
        mainpPnel.add(outgoing);
        mainpPnel.add(sendButton);
        
        //初始化参数
        setUpNetworking();

        // 启动一个客户端聊天线程
        new Thread(new IncomingReader()).start();

        frame.getContentPane().add(BorderLayout.CENTER, mainpPnel);
        frame.setVisible(true);
    }

    // 初始化socket连接的相关参数
    private void setUpNetworking() {
        try {
            socket = new Socket("127.0.0.1", 5000);
            InputStreamReader streamReader = new InputStreamReader(
                    socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("客户端连接建立起来了:"
                    + socket.getInetAddress().getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 发送消息的按钮触发监听器
    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            writer.println(outgoing.getText());
            writer.flush();
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    // 客户端具体的线程需要执行的Task任务
    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("客户端读到的信息为:" + message);
                    incoming.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                System.out.println("客户端程序关闭了。。。");
            }

        }

    }

}
