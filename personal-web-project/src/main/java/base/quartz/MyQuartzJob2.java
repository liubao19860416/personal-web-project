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
@Service("myQuartzJob2")
public class MyQuartzJob2 {
    
    private static Logger logger = LoggerFactory.getLogger(MyQuartzJob2.class);
    
    public static ArrayList<String> orderList = new ArrayList<String>();

    /**
     * 定时执行的任务
     */
    @SuppressWarnings("deprecation")
    public void work() {
            logger.info("MyQuartzJob2 Quartz startTime:" + new Date().toLocaleString());
            logger.info("MyQuartzJob2 Quartz endTime:" + new Date().toLocaleString());
            System.out.println("MyQuartzJob2测试代码。。。" + new Date().toLocaleString());
        }


}
