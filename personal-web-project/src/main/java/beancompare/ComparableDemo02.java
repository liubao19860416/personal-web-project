package beancompare;

import java.util.Date;

/**
 * 可以用实现了Comparable接口的对象给起实例化,直接输出待用对应的实际对象的额toString方法
 * 
 * @author Liubao
 * @2015年3月31日
 * 
 */
public class ComparableDemo02 {

    public static void main(String args[]) {
        Comparable com = null; // 声明一个Comparable接口对象

        com = 30; // 通过Integer为Comparable实例化
        System.out.println("内容为：" + com); // 调用的是toString()方法

        com = new Date(); // 通过Integer为Comparable实例化
        System.out.println("内容为：" + com); // 调用的是toString()方法
    }
};