package thread.thread2;
/**
 * 测试当Runnable和Thread中都存在覆写的run方法的时候，哪一个会被执行和不被执行！！！
 * @author Liubao
 * @2014年12月22日
 *
 */
public class TraditionalThread {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1:" + Thread.currentThread().getName());
                    //System.out.println("1:" + this.getName());
                }
            }
        };
        thread.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("2:" + Thread.currentThread().getName());

                }

            }
        });
        thread2.start();

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("runnable不会被执行 :"+ Thread.currentThread().getName());
                }
            }
        }) {
            /**
             * Thread覆写的run方法，因为Runnable匿名内部类任务对象也实现了run方法，但在这里Runnable覆写的run方法是不会被执行的；
             */
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread :"+ Thread.currentThread().getName());

                }
            }
        }.start();

    }

}
