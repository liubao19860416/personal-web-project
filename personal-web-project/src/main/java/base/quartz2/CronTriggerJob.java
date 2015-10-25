package base.quartz2;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
/**
 * CronTrigger测试类
 * 
 * cron表达式中，总共7个字段，其中日期（日，不是年）和周必须是互斥的，即必须有且只有一个要指定值，另一个不指定 
 * 格式"0/5 * * * * ? *"就是错误的，日和星期同时都没有被指定；
 * @author Liubao
 * @2014年11月25日
 *
 */
public class CronTriggerJob implements Job {
    
    private static Log logger = LogFactory.getLog(CronTriggerJob.class);

    /**
     * JobExecutionContext 是连接scheduler与job的桥梁
     * 
     * 只是具体的自动执行的任务详情
     */
    @SuppressWarnings("deprecation")
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        
        System.out.println("我的定时任务开始执行了。。。");
        
        JobDetail jobDetail = context.getJobDetail();
        String jobName = jobDetail.getName();
        logger.info(jobName + " Running at " + new Date().toLocaleString());
        System.out.println(jobName + " Running at " + new Date().toLocaleString());
        
        // JobDataMap 可给任务传递一些必要的运行参数
        JobDataMap dataMap = jobDetail.getJobDataMap();
        if(dataMap!=null){
            String username = dataMap.getString("username");
            String password = dataMap.getString("password");
            if (username == null||password==null) {
                throw new JobExecutionException("用户名或者密码为空。。。");
            }
            System.out.println(username+"===>>"+password);
        }
        
        try {
            //休息1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        System.out.println("我的定时任务执行结束了。。。");
    }
    
    
    // main测试方法
    public static void main(String[] args) throws Exception {
        //方式1
        method1();
        
        //方式2
        //method2();
    }
    
    /**
     * 调用执行定时任务，设置具体的一些定时器参数，方式1
     */
    public static void method1() throws Exception {
     // 获取定时调度管理器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        // Create the JobDetail
        JobDetail jobDetail = new JobDetail("CronTriggerJob",Scheduler.DEFAULT_GROUP, CronTriggerJob.class);
        
        //在JOB中传递的一些参数，可以通过该map进行传递
        JobDataMap jobDataMap=new JobDataMap();
        jobDataMap.put("username", "liubao");
        jobDataMap.put("password", "123456");
        jobDataMap.put("habbity", "看电视");
        jobDetail.setJobDataMap(jobDataMap);
        
        // Create a CronTrigger ， fires @每5秒钟执行一次  Mon-Fri
        String cronExpression = "0/5 * * * * ? *";
        // 2014年的周一至周五的23:59:59秒执行
        //cronExpression = "59 59 23 ? * MON-FRI 2014";
        // 2014年的周六日的每5秒钟执行一次 
       // cronExpression = "0/5 * * ? * SAT-SUN 2014";
        
        CronTrigger trigger = new CronTrigger("CronTrigger", null,cronExpression);
        
        scheduler.scheduleJob(jobDetail, trigger);
        
        //启动定时器
        scheduler.start();
        
        //关闭方法
        /*if(scheduler!=null){
            scheduler.shutdown();
        }*/
    }
    
    /**
     * 调用执行定时任务，设置具体的一些定时器参数，方式2
     */
    public static void method2() throws Exception {
        //创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
         //创建任务
        JobDetail jobDetail = new JobDetail("CronTriggerJob",Scheduler.DEFAULT_GROUP, CronTriggerJob.class);  
        CronTrigger cronTrigger = new CronTrigger("CronTrigger","CronTriggerGroup");
        // 创建触发器
        try {
         // Create a CronTrigger ， fires @每5秒钟执行一次  Mon-Fri
            String cronExpression = "0/5 * * * * ? *";
            // 2014年的周一至周五的23:59:59秒执行
            //cronExpression = "59 59 23 ? * MON-FRI 2014";
            CronExpression cexp = new CronExpression(cronExpression);
            cronTrigger.setCronExpression(cexp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 运行任务
        scheduler.scheduleJob(jobDetail, cronTrigger);
        
        //启动任务
        scheduler.start();
        
        //关闭方法
        /*if(scheduler!=null){
            scheduler.shutdown();
        }*/
        
    }

}
