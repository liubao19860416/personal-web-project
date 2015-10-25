package thread.thread2;
/**
 * 想成共享数据测试
 * @author Liubao
 * @2014年12月22日
 *
 */
public class MultiThreadShareData {

    /**
     * 全局静态变量：可以吗？？？
     */
    private static ShareData1 data1 = new ShareData1();

    public static void main(String[] args) {
        /**
         * 方式1：ok
         */
         ShareData1 data2 = new ShareData1();
         new Thread(new MyRunnable1(data2)).start();
         new Thread(new MyRunnable2(data2)).start();
        
        /**
         * 方式3：ok
         */
         new Thread(new MyRunnable1(data1)).start();
         new Thread(new MyRunnable2(data1)).start();

        /**
         * 方式2：ok
         */
        final ShareData1 data1 = new ShareData1();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    data1.decrement();
                }
            }
        })/*.start()*/;
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    data1.increment();
                }
            }
        })/*.start()*/;

    }
}
/**
 * 方式1：定义两个线程任务对象，通过构造方法传入共享的数据对象
 */
class MyRunnable1 implements Runnable {
    private ShareData1 data1;
    public MyRunnable1(ShareData1 data1) {
        this.data1 = data1;
    }
    public void run() {
        for (int i = 0; i < 5; i++) {
            data1.decrement();
        }
    }
}

class MyRunnable2 implements Runnable {
    private ShareData1 data1;
    public MyRunnable2(ShareData1 data1) {
        this.data1 = data1;
    }
    public void run() {
        for (int i = 0; i < 5; i++) {
            data1.increment();
        }
    }
}
/**
 * 方式2：将需要同步的方法，绑定到一个对象上面，方便进行操作
 */
class ShareData1 /* implements Runnable */{
    /*
     * private int count = 100;
     * @Override public void run() { 
     * while(true){ count--; } }
     */
    private int j = 0;
    public synchronized void increment() {
        j++;
        System.out.println(j);
    }
    public synchronized void decrement() {
        j--;
        System.out.println(j);
    }
}