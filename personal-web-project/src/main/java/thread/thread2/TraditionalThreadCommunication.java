package thread.thread2;

/**
 * 主线程和子线程交替执行
 * 
 * @author Liubao
 * @2014年12月22日
 *
 */
public class TraditionalThreadCommunication {

    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //执行50遍
                for (int i = 1; i <= 50; i++) {
                    business.method1(i);
                }
            }
        }).start();

        //执行50遍
        for (int i = 1; i <= 50; i++) {
            business.method2(i);
        }
    }
}

/**
 * 将需要进行同步操作的不同任务，集中到一个对象上面，统一进行调度的思想
 */
class Business {
    private boolean bShouldSub = true;
    public synchronized void method1(int i) {
        while (!bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //每遍执行10次
        for (int j = 1; j <= 10; j++) {
            System.out.println("sub thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = false;
        this.notify();
    }

    public synchronized void method2(int i) {
        while (bShouldSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //每遍执行100次
        for (int j = 1; j <= 100; j++) {
            System.out.println("main thread sequence of " + j + ",loop of " + i);
        }
        bShouldSub = true;
        this.notify();
    }
}
