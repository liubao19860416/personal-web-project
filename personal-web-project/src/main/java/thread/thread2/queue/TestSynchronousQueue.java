package thread.thread2.queue;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Lock;

import  sun.misc.ConditionLock;

/**
 * 测试同步队列的使用方法
 * 
 * @author Liubao
 * @2014年12月22日
 * 
 */
public class TestSynchronousQueue {

    public static void main(String[] args) {
        //信号灯量，1为允许的个数
        final Semaphore semaphore = new Semaphore(1);
        //final ConditionLock lock=new ConditionLock();
        //定义一个同步队列
        final SynchronousQueue<String> queue = new SynchronousQueue<String>();
        for (int i = 0; i < 10; i++) {
            /**
             * 定义一个线程任务，向该队列中取数值
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //相当于锁机制的lock
                        semaphore.acquire();
                        //lock.lock();
                        //取数据
                        String input = queue.take();
                        //操作该数据，定义为1秒钟取出一条数据
                        String output = TestDo.doSome(input);
                        System.out.println(Thread.currentThread().getName() + ":" + output);
                        //相当于锁机制的unlock
                        //lock.unlock();
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        /**
         * 在主线程中，向该队列中存放数据
         */
        for (int i = 0; i < 10; i++) {
            String input = i + "";
            try {
                queue.put(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 内部类，定义时间间隔为1秒钟 ；加上synchronized会变成单线程！！！
 *
 */
class TestDo {
    public static  /*synchronized*/ String doSome(String input) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String output = input + ":" + (System.currentTimeMillis() / 1000);
        return output;
    }
}
