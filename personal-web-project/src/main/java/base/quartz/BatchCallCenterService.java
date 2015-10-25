package base.quartz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 定时发送数据
 * 
 * quartz还有一个问题就是不支持cluster。导致使用quartz的应用都没有办法做群集。
 * 如果是我的话，我采取的办法就是自己单独启动一个Job Server，来跑job，不会部署在web容器中
 * 
 */
@Service("batchCallCenterService")
public class BatchCallCenterService {
    
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(BatchCallCenterService.class);
    
    //全局list，保存一些测试常量
    public static ArrayList<String> orderList = new ArrayList<String>();

    /**
     * 添加到list中，同步代码块实现
     */
    public boolean addData(String data) {
        addDataString(data);
        return true;
    }

    /**
     * 同步代码块实现
     */
    private synchronized void addDataString(String data) {
        orderList.add(data);
    }

    /**
     * 添加数据同步的方法
     */
    @SuppressWarnings("unused")
    private synchronized void addDataList(List<String> list) {
        for (String str : list) {
               orderList.add(str);
            }
        /*if(list!=null&&list.size()>0){
            orderList.addAll(list);
        }*/
    }
    
    /**
     * 同步的删除方法
     */
    @SuppressWarnings("unused")
    private synchronized void deleteDataString(String data) {
        orderList.remove(data);
        /*for (int i = 0; i < orderList.size(); i++) {
            if (data.equals(orderList.get(i))) {
                orderList.remove(i);
            }
        }*/
    }
    
    
    @SuppressWarnings("unused")
    private synchronized void deleteDataList(ArrayList<String> list) {
        for (String str : list) {
            for (int j = orderList.size() - 1; j >= 0; j--) {
                if (str.equals(orderList.get(j))) {
                    orderList.remove(j);
                }
            }
        }
       /* if(orderList!=null&&list!=null){
            orderList.removeAll(list);
        }*/
    }

    
    /**
     * 定时执行的任务
     */
    @SuppressWarnings("deprecation")
    public void work() {
            logger.info("Quartz startTime:" + new Date().toLocaleString());
            if(orderList!=null&&orderList.size()>0){
                //logger.info("Quartz start！！！size:" + orderList.size() + "  order:"+ orderList.toString());
                orderList.remove(0);
                //logger.info("Quartz end！！！size:" + orderList.size() + "order:"+ orderList.toString());
            }
            logger.info("Quartz endTime:" + new Date().toLocaleString());
            System.out.println("Quartz endTime:" + new Date().toLocaleString());
        }


}
