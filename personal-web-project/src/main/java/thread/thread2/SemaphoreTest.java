package thread.thread2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * 缓存线程池测试：当3个对象全了之后，在执行下一个循环的操作
 * @author Liubao
 * @2014年12月22日
 *
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        //缓存线程池
        ExecutorService service = Executors.newCachedThreadPool();
        //最多存在3个变量
        final Semaphore sp = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            //定义一个匿名任务对象
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        //上锁
                        sp.acquire();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("当前线程为" + Thread.currentThread().getName()
                            + "剩余个数" + (3 - sp.availablePermits()));
                    try {
                        Thread.sleep((long) (Math.random() * 1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //释放
                    sp.release();
                    System.out.println("当前线程为" + Thread.currentThread().getName()
                            + "剩余个数" + (3 - sp.availablePermits()));
                }
            };
            
            //执行线程任务Runnable对象
            service.execute(runnable);
        }
    }

}
