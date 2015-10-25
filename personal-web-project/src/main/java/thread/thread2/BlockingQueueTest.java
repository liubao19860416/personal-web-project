package thread.thread2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 队列:实现先进先出的效果
 * 
 * @author Liubao
 * @2014年12月27日
 * 
 */
public class BlockingQueueTest {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        final BlockingQueue queue = new ArrayBlockingQueue(3);
        for (int i = 0; i < 2; i++) {
            new Thread() {
                @SuppressWarnings("unchecked")
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep((long) (Math.random() * 1000));
                            System.out.println(Thread.currentThread().getName());
                            queue.put(1);
                            System.out.println(Thread.currentThread().getName()+":"+ queue.size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }.start();
        }

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        queue.take();
                        System.out.println(Thread.currentThread().getName() +":"+ queue.size());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();
    }
}
