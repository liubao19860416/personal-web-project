package thread.thread2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 读写锁测试类：在读锁解锁的时候，再执行写锁上锁；写锁解锁后，再回复读锁
 * 
 * @author Liubao
 * @2014年12月23日
 *
 */
public class ReadWriteLockDemo {

    private Map<String, Object> cache = new HashMap<String, Object>();

    public static void main(String[] args) {
       new ReadWriteLockDemo().getData("");
    }

    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    public Object getData(String key) {
        rwl.readLock().lock();
        Object value = null;
        try {
            value = cache.get(key);
            if (value == null) {
                /**
                 * 在读锁解锁的时候，再执行写锁上锁；写锁解锁后，再回复读锁
                 */
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if (value == null) {
                        value = "aaaa"; 
                    }
                } finally {
                    rwl.writeLock().unlock();
                }
                rwl.readLock().lock();
            }
        } finally {
            rwl.readLock().unlock();
        }
        return value;
    }
}
