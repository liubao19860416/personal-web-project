package base.quartz2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *   
 * SimpleTrigger测试类--不能被触发调用？？？？？？？
 * 
 * @author Liubao
 * @2014年11月25日
 *
 */
public class SimpleTriggerJob implements Job{
    
    private static Logger logger = LoggerFactory.getLogger(SimpleTriggerJob.class);

    //开始时间和结束时间
    @SuppressWarnings("unused")
    private static Date startTime=null;
    @SuppressWarnings("unused")
    private static Date endTime=null;
    
    //不被new
    public SimpleTriggerJob() {
        super();
    }
    
    /**
     * 需要执行的任务详情，会自动被执行
     */
    @SuppressWarnings("deprecation")
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("任务执行了。。。"+new Date().toLocaleString());
        logger.info("任务执行了。。。"+new Date().toLocaleString());
    }

    //测试main方法
    public static void main(String[] args) {
        try {
            startTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2014-11-25 15:18:00.000");
            endTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse("2014-11-30 12:00:00.000");
        } catch (ParseException e1) {
            logger.error("日期格式输入错误。。。");
            e1.printStackTrace();
        }
        
        // 任务详情对象
        JobDetail jobDetail = new JobDetail("jobdetail", "jobdetail-group-s1",SimpleTriggerJob.class);
        
        // 触发对象（包含任务触发规则）
        SimpleTrigger simpleTrigger = new SimpleTrigger("simpleTrigger","trigger-group-s1");
        //simpleTrigger.setStartTime(startTime); // 设置开始时间，系统当前时间10秒后
        simpleTrigger.setStartTime(new Date(System.currentTimeMillis() + 10000L));
        simpleTrigger.setRepeatInterval(1000); // 重复时间间隔
        simpleTrigger.setRepeatCount(5); // 重复次数
        //simpleTrigger.setTimesTriggered(5); // 触发次数
        //simpleTrigger.setEndTime(endTime); // 设置结束时间

        // 调度器对象
        Scheduler schedule=null;
        try {
            logger.info("定时任务开始执行了。。。");
            schedule = new StdSchedulerFactory().getScheduler();
            
            // 为调度器对象绑定“任务详情对象”和“调度器规则对象”
            schedule.scheduleJob(jobDetail, simpleTrigger);
            
            // 调度监控开始……
            schedule.start();
            
        } catch (SchedulerException e) {
            logger.error("定时任务执行异常。。。",e);
            e.printStackTrace();
        }finally{
            try {
                if(schedule!=null){
                    schedule.shutdown();
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
            }finally{
                schedule=null;
            }
        }
       //最终退出处理
        //System.exit(0);
    }
    

    

}
