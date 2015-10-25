package thread;

/**
 * 自定义线程1
 * 
 * @author Liubao
 * @2014年10月31日
 * 
 */
public class ThreadOne implements Runnable {

    Accum a = Accum.getAccum();

    public void run() {

        for (int i = 0; i < 98; i++) {
            a.updateCounter(1000);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("one:" + a.getCount());
        }

        System.out.println("FINALLY one:" + a.getCount());

    }

}
