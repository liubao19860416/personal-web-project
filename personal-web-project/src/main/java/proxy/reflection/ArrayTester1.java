package proxy.reflection;

import java.lang.reflect.Array;

/**
 * 测试反射包中的Array的使用，创建的相当于是一个反射的数组数据
 * 
 * @author Liubao
 * @2015年3月16日
 * 
 */
public class ArrayTester1 {

    public static void main(String args[]) throws Exception {
        Class<?> classType = Class.forName("java.lang.String");
        // Object array = Array.newInstance(classType, 10);
         Object array = Array.newInstance(String.class, 10);
        Array.set(array, 5, "hello");
        String s = (String) Array.get(array, 5);
        System.out.println(s);
        s = (String) Array.get(array, 4);
        System.out.println(s);
    }
}
