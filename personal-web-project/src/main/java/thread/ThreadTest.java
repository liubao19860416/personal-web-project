package thread;

/**
 * 测试守护线程：守护线程在主线程执行完毕之后，
 * 执行一段时间后，就会自动退出；不会是死循环
 * 
 * @author Liubao
 * @2014年12月2日
 * 
 */
public class ThreadTest {

    public static void main(String[] args) {
        
        //测试1
        new Thread(new ThreadOne()).start();
        new Thread(new ThreadTwo()).start();
        
        //测试2
        TaskA a = new TaskA();
        a.setDaemon(true);
        a.start();
        
        Thread b=new Thread(new TaskB());
        b.setDaemon(true);
        b.start();
        
        
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前执行线程为：" + Thread.currentThread().getName());
        System.out.println("主线程执行结束了。。。：");
    }

}

/**
 * 线程任务A
 */
class TaskA extends Thread {
    public void run() {
        while (true) {
            System.out.println("当前执行线程为：" + Thread.currentThread().getName());
        }
    }
}

/**
 * 线程任务B
 */
class TaskB implements Runnable {
    public void run() {
        while (true) {
            System.out.println("当前执行线程为：" + Thread.currentThread().getName());
        }
    }
}
