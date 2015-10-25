package thread.thread2.read;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * 测试BlockingQueue队列的使用
 * 
 * 开启4个线程去执行16个任务，每次一个线程只能执行一个任务，共执行4轮后16个任务全部执行完毕
 * @author Liubao
 * @2014年12月22日
 *
 */
public class TestBlockingQueue {

    public static void main(String[] args) {
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String log = queue.take();
                            parseLog(log);
                            System.out.println(Thread.currentThread().toString());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        /**
         * 相当于向队列中放入了16条消息
         */
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        for (int i = 0; i < 16; i++) {
            final String log = "" + (i + 1);
            {
                try {
                    queue.put(log);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Test.parseLog(log);
            }
        }
    }

    /**
     * 打印方法，相当于是一个任务
     */
    public static void parseLog(String log) {
        System.out.println(log + ":" + (System.currentTimeMillis() / 1000));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
