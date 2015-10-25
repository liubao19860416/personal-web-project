package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import base.entiy.User;

public class CollectionsTest {
    /**
     * 比较器的使用 TODO
     */
    @SuppressWarnings("unchecked")
    @Test
    public void test0() throws Exception {
        List<User> list = new ArrayList<User>();
        for (int i = 5; i > 0; i--) {
            User user = new User();
            user.setId(20 + i);
            user.setUsername("NAME");
            list.add(user);
        }

        User user = new User();
        user.setId(22);
        user.setUsername("NAME");
        list.add(user);

        System.out.println(list);
        Collections.sort(list, new UserCompare());
        System.out.println(list);
    }

    /**
     * Returns true if the two specified collections have no elements in common.
     * 如果两个集合没有相同的值，则返回true； 感觉：只能用来判断基本数据类型，否则需要覆写equals方法和String类型
     */
    @Test
    public void test01() throws Exception {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("a");
        list2.add("b");
        boolean disjoint = Collections.disjoint(list1, list2);
        Assert.assertFalse(disjoint);
    }

    /**
     * 注意拷贝属性的顺序，从list1拷贝到list2
     */
    @Test
    public void test02() throws Exception {
        List<User> list1 = new ArrayList<User>();
        User user1 = new User();
        user1.setId(22);
        user1.setUsername("NAME");

        List<User> list2 = new ArrayList<User>();
        User user2 = new User();
        list2.add(user2);

        // true if the collection changed as a result of the call
        boolean result = Collections.addAll(list1, user1);
        Assert.assertTrue(result);
        /**
         * 注意拷贝属性的顺序，从list1拷贝到list2
         */
        Collections.copy(list1, list2);
        Assert.assertTrue(list2.size() > 0);
    }

    /**
     * 对象属性进行替换
     */
    @Test
    public void test03() throws Exception {
        List<User> list1 = new ArrayList<User>();
        User user1 = new User();
        user1.setId(21);
        user1.setUsername("NAME1");
        User user2 = new User();
        user2.setId(22);
        user2.setUsername("NAME2");
        list1.add(user1);
        list1.add(user2);

        User user3 = new User();
        user3.setId(23);
        user3.setUsername("NAME3");

        boolean result = Collections.replaceAll(list1, user1, user3);
        Assert.assertTrue(result);
    }

    /**
     * 其他工具方法测试
     */
    @Test
    public void test04() throws Exception {
        List<User> list = new ArrayList<User>();
        User user1 = new User();
        user1.setId(21);
        user1.setUsername("NAME1");
        User user2 = new User();
        user2.setId(22);
        user2.setUsername("NAME2");
        User user3 = new User();
        user3.setId(23);
        user3.setUsername("NAME3");
        list.add(user2);
        list.add(user1);
        list.add(user1);

        // 测试出现的频率
        int frequency = Collections.frequency(list, user1);
        Assert.assertEquals(2, frequency);
        frequency = Collections.frequency(list, user3);
        Assert.assertEquals(0, frequency);

        List<User> list2 = new ArrayList<User>();
        list2.add(user1);
        // the list in which to search for the first occurrence of list2.
        int indexOfSubList = Collections.indexOfSubList(list, list2);
        Assert.assertEquals(1, indexOfSubList);

        indexOfSubList = Collections.lastIndexOfSubList(list, list2);
        Assert.assertEquals(2, indexOfSubList);

        User user4 = new User();
        user4.setId(29);
        user4.setUsername("NAME9");
        /*
         * 将user4中的属性覆盖到list中的全部元素
         */
        Collections.fill(list, user4);
        List<String> list3 = new ArrayList<String>();
        list3.add("d");
        list3.add("4");
        list3.add("a");
        /**
         * 获取最大值
         */
        String max = Collections.max(list3);
        Assert.assertNotNull(max);

        /**
         * n the number of elements in the returned list. o the element to
         * appear repeatedly in the returned list. 返回一个有n个拷贝对象的list数组
         */
        List<User> nCopies = Collections.nCopies(3, user4);
        Assert.assertEquals(3, nCopies.size());

        // 反转方法：方式1
        System.out.println(list3);
        Collections.reverse(list3);
        System.out.println(list3);

        // 反转方法：方式2
        Collections.sort(list3, Collections.reverseOrder());
        System.out.println(list3);

        /**
         * 指定角标交换
         */
        Collections.swap(list3, 0, 2);
        System.out.println(list3);

        /**
         * 生成一个同步集合
         */
        List<String> synchronizedList = Collections.synchronizedList(list3);
        Assert.assertNotNull(synchronizedList);

    }

    /**
     * 姓名相同的，按照id进行升序排列
     */
    @SuppressWarnings("rawtypes")
    class UserCompare implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            boolean flag = true;
            User user1 = null;
            User user2 = null;
            if (o1 instanceof User) {
                user1 = (User) o1;
            } else {
                flag = false;
            }
            if (o2 instanceof User) {
                user2 = (User) o1;
            } else {
                flag = false;
            }
            if (flag) {
                if (user1.getUsername().equals(user2.getUsername())) {
                    return user1.getId() - user2.getId();
                } else {
                    return user1.getUsername().compareTo(user2.getUsername());
                }
            }
            return 0;
        }
    }
}