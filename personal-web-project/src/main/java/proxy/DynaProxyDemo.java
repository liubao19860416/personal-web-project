package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;

interface Subject {
    public String say(String name, int age); // 定义抽象方法say
}

class RealSubject implements Subject { // 实现接口
    @Override
    public String say(String name, int age) {
        return "姓名：" + name + "，年龄：" + age;
    }
};

class MyInvocationHandler implements InvocationHandler {
    private Object obj;

    public Object bind(Object obj) {
        this.obj = obj; // 真实主题类
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
                .getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object temp = method.invoke(this.obj, args); // 调用方法
        return temp;
    }
};

public class DynaProxyDemo {
    public static void main(String args[]) {
        Subject sub = (Subject) new MyInvocationHandler()
                .bind(new RealSubject());
        String info = sub.say("李兴华", 30);
        System.out.println(info);
    }
};