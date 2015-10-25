package thread;

import java.util.Random;

public class TheradTask implements Runnable {
    public void run() {
        System.out.println("Runnable:"+Thread.currentThread().getName());
        try {
            //这样能--尽量--保证不被多个线程访问的时间，不被同时触发
            Thread.sleep(new Random().nextInt(60));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
