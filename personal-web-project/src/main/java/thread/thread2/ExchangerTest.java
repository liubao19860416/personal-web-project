package thread.thread2;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 交换队列：当偶线程来进行交换数据的时候，交换才会发生，否则处于交换等待的阻塞状态
 */
public class ExchangerTest {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        //缓存线程池执行器
        ExecutorService service = Executors.newCachedThreadPool();
        //同一个交换对象
        final Exchanger exchanger = new Exchanger();
        service.execute(new Runnable() {
            public void run() {
                try {
                    String data1 = "zxx";
                    System.out.println("交换前：" + Thread.currentThread().getName() + "：" + data1 );
                    Thread.sleep((long) (Math.random() * 10000));
                    String data2 = (String) exchanger.exchange(data1);
                    System.out.println("交换后：" + Thread.currentThread().getName()+ "：" + data2);
                } catch (Exception e) {
                }
            }
        });
        service.execute(new Runnable() {
            public void run() {
                try {
                    String data1 = "lhm";
                    System.out.println("交换前：" + Thread.currentThread().getName() + "：" + data1 );
                    Thread.sleep((long) (Math.random() * 10000));
                    String data2 = (String) exchanger.exchange(data1);
                    System.out.println("交换后：" + Thread.currentThread().getName()+ "：" + data2);
                } catch (Exception e) {
                }
            }
        });
    }
}
