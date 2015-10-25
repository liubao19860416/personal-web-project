package thread.pool;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 测试执行线程的类:获取线程池对象，调用其执行方法即可
 * 
 * @author Liubao
 * @2014年11月26日
 * 
 */
public class MyTaskExecutorTest2 {
    
    //静态存储器
   private static Map<Integer, String> params=new HashMap<Integer, String>();

    @SuppressWarnings({ "resource" })
    public static void main(String args[]) {
        
        //读取配置文件
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:conf/applicationContext-threadPool.xml");

        //其本身就是已经同步了的
        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) appContext.getBean("taskExecutor");
        //可以传递封装的参数信息等
      
        for(int i=0;i<100;i++){
            System.out.println("当前执行次数为---"+i);
            //执行多线程任务
            taskExecutor.execute(new MessagePrinterTask2(params));
            
        }
        
        System.out.println("当前活动线程个数======:"+taskExecutor.getActiveCount());
        System.out.println("当前线程池中线程数========:"+taskExecutor.getPoolSize());
        
        //关闭线程池
        //taskExecutor.shutdown();
        //taskExecutor.destroy();
        
        System.out.println("===========================线程池中总共开启的线程个数："+params.keySet().size()+"，当前主线程为："+Thread.currentThread().toString()+"，hashCode为：" + Thread.currentThread().hashCode());
    
        //打印输出结果数据
        for (Integer key : params.keySet()) {
            String value = params.get(key);
            System.out.println(key+"=====》》"+value);
        }
    }
    

}
