package thread.thread2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 需求：当所有的对象都完成了一步操作后，再继续下面的操作的情形
 * 
 * 即：让当前线程处于一种等待状态，相当于wait方法
 * @author Liubao
 * @2014年12月27日
 *
 */
public class CountdownLatchTest {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        System.out.println("4:" + Thread.currentThread().getName());
                        //等待位置，当3个线程都完成了这里，再执行下面的操作
                        cdOrder.await();
                        System.out.println("5:" + Thread.currentThread().getName());
                        Thread.sleep((long) (Math.random() * 1));
                        System.out.println("6:" + Thread.currentThread().getName());
                        cdAnswer.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        
        try {
            Thread.sleep((long) (Math.random() * 1));
            System.out.println("1:" + Thread.currentThread().getName());
            cdOrder.countDown();
            System.out.println("2:" + Thread.currentThread().getName());
            cdAnswer.await();
            System.out.println("3:" + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();

    }
}
