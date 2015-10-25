package thread.thread2;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * Future：获取将要返回的值，相当于反射的invoke方法
 * CompletionService：可以异步取出之前顺序放入的数据
 * @author Liubao
 * @2014年12月27日
 *
 */
public class CallableAndFuture {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(2000);
                return "hello";
            };
        });
        
        System.out.println("OK");
        
        try {
            System.out.println("future:" + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        //ExecutorService threadPool2 = Executors.newFixedThreadPool(1);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
       
        /**
         * 放数据的操作
         */
        for (int i = 1; i <= 10; i++) {
            final int seq = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    //Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
        }
        
        /**
         * 取数据的操作
         */
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
