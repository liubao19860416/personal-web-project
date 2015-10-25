package thread.thread2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCommunication {

    public static void main(String[] args) {
        final Business business = new Business();
        //子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    business.sub(i);
                }
            }
        }).start();
        //主线程
        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }

    }

    /**
     * 封装线程任务的对象
     * 子线程和主线程，通过切换标记bShouldSub进行轮流执行
     */
    static class Business {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        /**
         * 子线程和主线程，通过切换标记bShouldSub进行轮流执行
         */
        private boolean bShouldSub = true;

        public void sub(int i) {
            lock.lock();
            try {
                while (!bShouldSub) {
                    try {
                        condition.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("sub thread sequence of " + j
                            + ",loop of " + i);
                }
                bShouldSub = false;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void main(int i) {
            lock.lock();
            try {
                while (bShouldSub) {
                    try {
                        condition.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 100; j++) {
                    System.out.println("main thread sequence of " + j
                            + ",loop of " + i);
                }
                bShouldSub = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

    }
}
