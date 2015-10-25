package thread.pool;

import java.util.Map;

/**
 * 这样的话，就是在程序代码中主动启动的多线程，而另外一种方式是通过xml配置实现的
 * 
 * @author Liubao
 * @2014年11月26日
 * 
 */
public class MessagePrinterTask2 implements Runnable {
    private Map<Integer, String> params;

    /**
     * 使用构造方法注入的方式
     */
    public MessagePrinterTask2(Map<Integer, String> params) {
        this.params = params;
    }

    /**
     * 核心的run方法
     */
    @Override
    public void run() {
        
        // 获取需要的对象，执行自定义的任务等操作
        //BatchCallCenterService service=(BatchCallCenterService)ApplicationContextHelper.getBean("myJobTaskBean1");
        //操作当前线程的静态list对象，该对象在多个线程之间是共享的
        //service.addData(String.valueOf(params.get(1)));
        //System.out.println(service.orderList.toString());
        
        //key为当前线程的唯一标识即可
        params.put(Thread.currentThread().hashCode(),Thread.currentThread().toString());
        
        //System.out.println("在子线程执行了："+Thread.currentThread().toString()+"，hashCode为：" + Thread.currentThread().hashCode());
        //System.out.println(params.toString());
        //System.out.println("****************************************************");
    
    }
    
}
