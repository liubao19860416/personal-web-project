package map;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * HashMap比较的是equals，IdentitiHashMap比较的是对象地址（是引用而不是值）
 * 
 * @author Liubao
 * @2015年3月31日
 * 
 */

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person p = (Person) obj;
        if (this.name.equals(p.name) && this.age == p.age) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.name.hashCode() * this.age;
    }

    public String toString() {
        return "姓名：" + this.name + "，年龄：" + this.age;
    }
};

public class IdentityHashMapDemo0 {
    
    public static void main(String args[]) {
        method01();
        System.out.println("====================");
        method02();
    }

    private static void method02() {
        Map<Person, String> map = null; // 声明Map对象
        map = new IdentityHashMap<Person, String>();
        map.put(new Person("张三", 30), "zhangsan_1"); // 加入内容
        map.put(new Person("张三", 30), "zhangsan_2"); // 加入内容
        map.put(new Person("李四", 31), "lisi"); // 加入内容
        Set<Map.Entry<Person, String>> allSet = null; // 准备使用Set接收全部内容
        allSet = map.entrySet();
        Iterator<Map.Entry<Person, String>> iter = null;
        iter = allSet.iterator();
        while (iter.hasNext()) {
            Map.Entry<Person, String> me = iter.next();
            System.out.println(me.getKey() + " --> " + me.getValue());
        }
    }

    private static void method01() {
        Map<Person, String> map = null; // 声明Map对象
        map = new HashMap<Person, String>();
        map.put(new Person("张三", 30), "zhangsan_1"); // 加入内容
        map.put(new Person("张三", 30), "zhangsan_2"); // 加入内容
        map.put(new Person("李四", 31), "lisi"); // 加入内容
        Set<Map.Entry<Person, String>> allSet = null; // 准备使用Set接收全部内容
        allSet = map.entrySet();
        Iterator<Map.Entry<Person, String>> iter = null;
        iter = allSet.iterator();
        while (iter.hasNext()) {
            Map.Entry<Person, String> me = iter.next();
            System.out.println(me.getKey() + " --> " + me.getValue());
        }
    }

};