package thread.thread2.syn;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 测试集合不能在迭代的时候，进行并发修改操作的解决方案，使用CopyOnWriteArrayList替代
 * 
 * 测试同步标记的区别：字符串常量的引用与字符串常量的区别
 * 
 * @author Liubao
 * @2014年12月22日
 * 
 */
public class TestCopyOnWriteArrayList extends Thread {

    private TestDo testDo;
    private String key;
    private String value;

    public TestCopyOnWriteArrayList(String key, String key2, String value) {
        this.testDo = TestDo.getInstance();
        this.key = key + key2;
        /*
         * 这时候，a和b是等价的；
         * a = "1"+""; 
         * b = "1"+""
         * 当在执行this.key = key + key2;的时候，同步标记会发生改变
         */
        this.value = value;
    }

    public static void main(String[] args) throws InterruptedException {
        TestCopyOnWriteArrayList a = new TestCopyOnWriteArrayList("1", "", "1");
        TestCopyOnWriteArrayList b = new TestCopyOnWriteArrayList("1", "", "2");
        TestCopyOnWriteArrayList c = new TestCopyOnWriteArrayList("3", "", "3");
        TestCopyOnWriteArrayList d = new TestCopyOnWriteArrayList("4", "", "4");
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        a.start();
        b.start();
        c.start();
        d.start();

    }

    public void run() {
        testDo.doSome(key, value);
    }
}

class TestDo {
    private TestDo() {
    }
    private static TestDo _instance = new TestDo();
    public static TestDo getInstance() {
        return _instance;
    }

    // private ArrayList keys = new ArrayList();
    private CopyOnWriteArrayList keys = new CopyOnWriteArrayList();

    public void doSome(Object key, String value) {
        /**
         * 同步标记使用该对象的引用，否则的话，两者不想等
         * 这时候，a和b是才是等价的；
         * a = "1"+""; 
         * b = "1"+""
         */
        Object o = key;
        if (!keys.contains(o)) {
            keys.add(o);
        } else {
            for (Iterator iter = keys.iterator(); iter.hasNext();) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object oo = iter.next();
                if (oo.equals(o)) {
                    o = oo;
                    break;
                }
            }
        }
        /**
         * 这里的同步标记，需要借助一个引用Object o = key;，而不是直接使用传入的对象！！！
         */
        synchronized (o)
        {
            try {
                Thread.sleep(1000);
                System.out.println(key + ":" + value + ":"+ (System.currentTimeMillis() / 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
