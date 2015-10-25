package thread.pool;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

/**
 * 线程池测试类1：使用构造方法注入
 * 
 * 任务执行器
 * 输出格式：Thread[main,5,main]
 * 第一个main代表当前线程的名字（如果你没有命名，系统默认从0开始0、1、2、3。。。，中间的5代表优先级，第三个main表示线程所处的线程组）
 * 
 * @author Liubao
 * @2014年11月26日
 * 
 */
@Service
public class MyTaskExecutor {
    
    /**
     * 不可以省略
     */
    public MyTaskExecutor() {
        super();
    }
    
    // 对象属性，名称可以和构造方法注入的name不一样
    private TaskExecutor taskExecutor;

    // 构造方法注入
    public MyTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void printMessages() {
        for (int i = 0; i < 25; i++) {
            //这里它会自动调用实现了Runnable接口的自定义Task类的run方法
            taskExecutor.execute(new MessagePrinterTask("Message" + i));
        }
    }

}
