package _test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 生产者和消费者模式测试（管道流通信）
 */
public class PipedStreamTest {
    public static void main(String[] args) {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream();
        try {
            pos.connect(pis);
            new Producer(pos).start();
            new Consumer(pis).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

/**
 * 生产者线程
 */
class Producer extends Thread {
    private PipedOutputStream pos;
    private int index=0;

    public Producer(PipedOutputStream pos) {
        this.pos = pos;
    }

    public void run() {
        try {
            while(true){
                Thread.sleep(1000);
                pos.write("Hello,welcome you!".getBytes());
                System.out.println("生产者执行了【"+ ++index+"】次");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(pos!=null){
                    pos.close();
                    pos=null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费者线程
 */
class Consumer extends Thread {
    private PipedInputStream pis;
    private int index=0;

    public Consumer(PipedInputStream pis) {
        this.pis = pis;
    }

    public void run() {
        try {
            byte[] buf = new byte[100];
            while(true){
                Thread.sleep(2000);
                int len = pis.read(buf);
                System.out.println("消费者接收到的数据【"+ ++index+"】："+new String(buf, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(pis!=null){
                    pis.close();
                    pis=null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}