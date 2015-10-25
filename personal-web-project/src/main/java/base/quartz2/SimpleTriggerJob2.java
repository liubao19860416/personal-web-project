package base.quartz2;

/**
 * 这里需要进行静态导入！！！
 * import static org.quartz.CronScheduleBuilder.cronSchedule;
 * import static org.quartz.JobBuilder.newJob;
 * import static org.quartz.TriggerBuilder.newTrigger;
 */
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
/**
 *   
 * SimpleTrigger测试类  --
 * 注意：要static导入包，同时将scheduler的shutdown注释掉
 * 
 * @author Liubao
 * @2014年11月25日
 * 
 *
 */
public class SimpleTriggerJob2 implements Job{
    
    //全局常量
    private static int cnt = 0;  
    
    //空构造方法，私有不能new？？？
    // Instances of Job must have a public no-argument constructor.  
    private SimpleTriggerJob2() {  
    }  
    
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("SimpleTriggerJob2 " + (++cnt) + " Run! ");          
    } 
    
    //全局
    private static Scheduler scheduler;  
    
    public static void main(String[] args) {  
  
        try {  
            // Grab the Scheduler instance from the Factory   
            scheduler = StdSchedulerFactory.getDefaultScheduler();  
  
            // Set Scheduler  
            setScheduler();  
  
            // and start it off  
            scheduler.start();  
  
            //shutdown to see the output of TestJob  
//            scheduler.shutdown();    
  
        } catch (SchedulerException se) {  
            se.printStackTrace();  
        }  
    }  
    
    /**
     * 创建简单任务详情
     */
    private static void setScheduler(){  
       /* JobDetail job = newJob(SimpleTriggerJob2.class).withIdentity("job1",
                "group1").build();

        
        //TODO 需要修改
       ScheduleBuilder s = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatSecondlyForever();
        // Trigger the job to run now, and then repeat every 5 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(s
                        ).build();

        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }*/ 
    }
   

}
