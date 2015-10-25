package base.quartz;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 定时发送数据
 * 
 */
@Service("myQuartzJob3")
public class MyQuartzJob3 {
    
    private static Logger logger = LoggerFactory.getLogger(MyQuartzJob3.class);
    
    public static ArrayList<String> orderList = new ArrayList<String>();

    /**
     * 定时执行的任务
     */
    @SuppressWarnings("deprecation")
    public void work() {
            logger.info("定时执行的任务-MyQuartzJob3 Quartz startTime:" + new Date().toLocaleString());
            System.out.println("定时执行的任务-MyQuartzJob3测试代码。。。" + new Date().toLocaleString());
            logger.info("定时执行的任务-MyQuartzJob3 Quartz endTime:" + new Date().toLocaleString());
        }


}
