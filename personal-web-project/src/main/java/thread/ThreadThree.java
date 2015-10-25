package thread;

import org.junit.Test;

/**
 * 测试线程run方法 在上面的例子中，当Thread线程没有启动之后，首先寻找自己有没有覆写父类的run方法；
 * 如果子类覆写了父类的run方法，则直接执行子类的run方法；这样Runnable对象和其中的run方法就无效了；
 * 只有当期没有覆写父类的run方法的时候，他才会去父类中查找对应的run方法，然后判断其是否存在Runnable对象；
 * 当Runnaable对象存在的时候，执行Runnable对象存在的时候，执行Runnable独享的run方法；当其为null的时候，执行父类的run方法；
 * 
 * @author Liubao
 * @2014年11月17日
 * 
 */
public class ThreadThree extends Thread {

    public ThreadThree() {
        super();
    }

    public ThreadThree(Runnable target) {
        super(target);
    }

    @Override
    public void run() {
        System.out.println("Thread:" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new ThreadThree().start();//Thread:Thread-0
        new ThreadThree(new TheradTask()).start();// Thread:Thread-0
        // System.out.println(Thread.currentThread().getName());//main
    }

    /**
     * 结果是：Thread:Thread-0
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        new Thread(new Runnable(){
            public void run() {
                System.out.println("Thread:"+Thread.currentThread().getName());                
            }}){
            @Override
            public void run() {
                System.out.println("Runnable:"+Thread.currentThread().getName());            
            }
        }.start();
    }
}
