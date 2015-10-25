package base.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * 初始化参数的监听器
 * 
 * @author Liubao
 * @2014年11月25日
 *
 */
@SuppressWarnings("rawtypes")
public class InitParamtersListener implements ApplicationListener{

    private static Logger logger = LoggerFactory.getLogger(InitParamtersListener.class);
    
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        
        System.out.println("InitParamtersListener初始化参数的监听器执行了。。。");
        logger.info("InitParamtersListener初始化参数的监听器执行了。。。" );
        
        //当Spring的Context初始化完成之后就会触发ContextRefreshedEvent
        if(event instanceof ContextRefreshedEvent)   
        {   
            System.out.println("InitParamtersListener初始化参数的监听器执行了2。。。");
            logger.info("InitParamtersListener初始化参数的监听器执行了2。。。" );
        }   
        //Context启动后的事件
        if(event instanceof ContextStartedEvent)   
        {   
        }   
        //Context停止后的事件
        if(event instanceof ContextStoppedEvent)   
        {   
        }   
        //Context关闭时的事件
        if(event instanceof ContextClosedEvent)   
        {   
        }   
        
    }

}
