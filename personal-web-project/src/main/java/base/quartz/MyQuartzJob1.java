package base.quartz;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 定时发送数据
 * 
 * quartz还有一个问题就是不支持cluster。导致使用quartz的应用都没有办法做群集。
 * 如果是我的话，我采取的办法就是自己单独启动一个Job Server，来跑job，不会部署在web容器中
 */
@Service("myQuartzJob1")
public class MyQuartzJob1 {
    
    private static Logger logger = LoggerFactory.getLogger(MyQuartzJob1.class);
    
    public static ArrayList<String> orderList = new ArrayList<String>();

    /**
     * 定时执行的任务
     */
    @SuppressWarnings("deprecation")
    public void work() {
            logger.info("MyQuartzJob1 Quartz startTime:" + new Date().toLocaleString());
            logger.info("MyQuartzJob1 Quartz endTime:" + new Date().toLocaleString());
            System.out.println("MyQuartzJob1测试代码。。。" + new Date().toLocaleString());
        }


}
