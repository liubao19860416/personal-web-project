package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExceptionTest {

    public static void main(String args[]) {
        final ThreadPoolExecutor poolExecutor = 
                new ThreadPoolExecutor(20, 30, 2000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(20));
        
        poolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread();
                    Thread.sleep(3000l);
                    throw new RuntimeException("线程池内主动抛出异常信息...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread();
                    Thread.sleep(1000l);
                    throw new RuntimeException("线程任务内主动抛出异常信息...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end....");
    }
}
