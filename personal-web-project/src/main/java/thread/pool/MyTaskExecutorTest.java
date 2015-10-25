package thread.pool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试执行线程的类
 * 
 * @author Liubao
 * @2014年11月26日
 * 
 */
/*@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration( locations = { 
    "classpath:conf/applicationContext-threadPool.xml",
})*/
public class MyTaskExecutorTest {

    @SuppressWarnings("resource")
    public static void main(String args[]) {
        
        //读取配置文件
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:conf/applicationContext-threadPool.xml");

        //调用方式1
        MyTaskExecutor myTaskExecutor = (MyTaskExecutor) appContext.getBean("myTaskExecutor");
        myTaskExecutor.printMessages();
        
        //调用方式2
        //MyTaskExecutor2 myTaskExecutor2 = (MyTaskExecutor2) appContext.getBean("myTaskExecutor2");
        //myTaskExecutor2.printMessages();
        
        System.out.println("主线程结束了："+Thread.currentThread().toString()+"，hashCode为：" + Thread.currentThread().hashCode());
    }

}
