package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * JAVA5的线程池相关测试
 * @author Liubao
 * @2014年12月16日
 *
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        // 创建缓存的线程池服务
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"任务1执行了。。。");
            }
        });
        //创建固定大小的线程池
        executorService=Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"任务2执行了。。。");
            }
        });
        //创建单一的线程池，该线程死掉之后，会自动重新创建一个该线程池
        executorService=Executors.newSingleThreadScheduledExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"任务3执行了。。。");
            }
        });
        //创建线程工程名字对象
        //ThreadFactory threadFactory=new ThreadFactoryImpl("myThreadFactory");
        //定时任务线程池
        executorService=Executors.newScheduledThreadPool(10);
        try {
            //线程任务等待多久之后，再执行线程任务
            executorService.awaitTermination(10, TimeUnit.SECONDS);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread.currentThread().getName()+任务4执行了。。。");
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //线程任务执行完毕后，自动关闭线程池服务
        executorService.shutdown();
        //立刻关闭线程池服务，不管线程任务是否执行完毕。
        executorService.shutdownNow();
    }

}
