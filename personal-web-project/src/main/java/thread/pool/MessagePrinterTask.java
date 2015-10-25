package thread.pool;

import java.util.Date;

/**
 * 内部类，测试使用
 * 
 * @author Liubao
 * @2014年11月26日
 * 
 */
public class MessagePrinterTask implements Runnable {
    
    //对象的一些属性，可以自己定义
    private String message;

    // 构造方法
    public MessagePrinterTask(String message) {
        this.message = message;
    }

    /**
     *  最终要被自动多线程调用执行的任务内容。
     *  
     *  通过hashcode区分是否是同一个线程
     */
    @SuppressWarnings("deprecation")
    public void run() {
        System.out.println("当前时间["+ new Date(System.currentTimeMillis()).toLocaleString()
                + "]，当前线程为："+Thread.currentThread().toString()+"，hashCode为：" + Thread.currentThread().hashCode()+ "，消息内容为："+ message);
        
        try {
            // 睡眠5秒钟之后，结束当前线程；
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("当前时间["+ new Date(System.currentTimeMillis()).toLocaleString()
                + "]，当前线程为："+Thread.currentThread().toString()+"，hashCode为：" + Thread.currentThread().hashCode()+ "，消息内容为："+ message);
        
    }
    
}
