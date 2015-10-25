package base.quartz.task;

/**
 *  完成具体的任务操作 
 */
import java.util.TimerTask;
import java.util.Date;
import java.text.SimpleDateFormat;

// 任务调度类都要继承TimerTask
public class MyTask extends TimerTask {
    public void run() {
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println("当前系统时间为：" + sdf.format(new Date()));
    }
};