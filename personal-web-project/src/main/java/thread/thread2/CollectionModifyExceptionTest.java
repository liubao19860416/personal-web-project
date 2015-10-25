package thread.thread2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 并发修改集合会抛出，解决方法：CopyOnWriteArrayList
 * 
 * @author Liubao
 * @2014年12月27日
 * 
 */

public class CollectionModifyExceptionTest {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        
        //Collection users = new CopyOnWriteArrayList();
        Collection users = new ArrayList();

        users.add(new User("name1", 28));
        users.add(new User("name2", 25));
        users.add(new User("name3", 31));
        Iterator itrUsers = users.iterator();
        while (itrUsers.hasNext()) {
            User user = (User) itrUsers.next();
            if ("name1".equals(user.getName())) {
                users.remove(user);
                /**
                 * 解决方案2
                 */
                //itrUsers.remove();
            } else {
                System.out.println(user);
            }
        }
    }
}
