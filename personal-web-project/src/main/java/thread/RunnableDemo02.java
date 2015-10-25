package thread;

// 继承Thread类，作为线程的实现类
class MyThread implements Runnable {
    private String name; // 表示线程的名称

    public MyThread(String name) {
        this.name = name; // 通过构造方法配置name属性
    }

    private static /*volatile*/ int ticket = 10; // 表示一共有5张票

    /**
     * 相当于单线程了，在这个同步的位置
     */
    public /*synchronized*/ void run() { // 覆写run()方法，作为线程 的操作主体
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized(MyThread.class){
            for (int i = 0; i < 5; i++) {
                if (this.ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "==>"
                            + name + "[卖票：ticket = " + ticket--);
                }
            }
        }
    }
};

public class RunnableDemo02 {
    
    public static void main(String args[]) {

//        test01();

         test02();
    }

    private static void test02() {
        MyThread mt1 = new MyThread("线程A"); // 实例化对象
        MyThread mt2 = new MyThread("线程B"); // 实例化对象
        new Thread(mt1).run(); // 调用线程主体
        new Thread(mt1).run(); // 调用线程主体
        new Thread(mt2).run(); // 调用线程主体
        new Thread(mt2).run(); // 调用线程主体
    }

    /**
     * 正确的方式
     */
    private static void test01() {
        MyThread mt = new MyThread("线程A"); // 实例化对象
        new Thread(mt).start(); // 调用线程主体
        new Thread(mt).start(); // 调用线程主体
        new Thread(mt).start(); // 调用线程主体
    }
};