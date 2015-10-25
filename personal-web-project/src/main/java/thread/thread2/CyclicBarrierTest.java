package thread.thread2;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    private static final int SIZE = 5;

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        /**
         * 需求：当3个对象全部到全后，才能继续执行下面的操作
         * cb.getNumberWaiting()为当前等待的人数
         * 当cb.getNumberWaiting()等于下面的3的时候，就会继续执行下面的操作；否则会一直等待
         */
        final CyclicBarrier cb = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep((long) (new Random().nextInt(3) * 1000));
                        System.out.println(Thread.currentThread().getName()+":"+
                                        + (cb.getNumberWaiting()));
                        cb.await();
                        
                        Thread.sleep((long) (new Random().nextInt(3) * 1000));
                        System.out.println(Thread.currentThread().getName()+":"+
                                + (cb.getNumberWaiting()));
                        cb.await();
                        
                        Thread.sleep((long) (new Random().nextInt(3) * 1000));
                        System.out.println(Thread.currentThread().getName()+":"+
                                + (cb.getNumberWaiting()));
                        cb.await();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }
}
