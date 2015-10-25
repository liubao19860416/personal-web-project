package thread;

/**
 * 自定义线程2
 * 
 * @author Liubao
 * @2014年10月31日
 * 
 */
public class ThreadTwo implements Runnable {

    Accum a = Accum.getAccum();

    public void run() {

        for (int i = 0; i < 98; i++) {
            a.updateCounter(1);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("two:" + a.getCount());
        }

        System.out.println("FINALLY two:" + a.getCount());

    }

}
