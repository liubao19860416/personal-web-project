package thread;

import base.entiy.User;

class MyThread1 implements Runnable {
    private boolean flag = true; // 定义标志位

    public void run() {
        int i = 0;
        while (this.flag) {
            System.out.println(Thread.currentThread().getName() + "运行，i = "
                    + (i++));
        }
    }

    public void stop() {
        this.flag = false; // 修改标志位
    }
};


class  StaticPropertiesClass{
    public static User user=new User();
    public static String str=new String();
    
}

public class StopThread {
    
    public static void main(String args[]) {
        //相等的，因为实在静态初始化的时候，实例化的类的静态变量，共享的全局变量
        System.out.println(new StaticPropertiesClass().user.hashCode());
        System.out.println(new StaticPropertiesClass().user.hashCode());
        System.out.println(new StaticPropertiesClass().str.hashCode());
        System.out.println(new StaticPropertiesClass().str.hashCode());
        MyThread1 my = new MyThread1();
        Thread t = new Thread(my, "线程"); // 建立线程对象
        t.start(); // 启动线程
        try {
            Thread.sleep(30);
        } catch (Exception e) {

        }
        my.stop(); // 修改标志位，停止运行
    }
};