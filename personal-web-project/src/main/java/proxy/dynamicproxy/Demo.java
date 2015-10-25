package proxy.dynamicproxy;

import java.lang.reflect.Proxy;

public class Demo {
    public static void main(String[] args) {

        CommonInvocationHandler handler = new CommonInvocationHandler();
        Foo f;
        handler.setTarget(new FooImpl());

        f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),
                new Class[] { Foo.class }, handler);

        f.doAction();

        handler.setTarget(new FooImpl2());

        f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),
                new Class[] { Foo.class }, handler);

        f.doAction();
    }
}
