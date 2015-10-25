package proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CommonInvocationHandler implements InvocationHandler {

    private Object target;

    public CommonInvocationHandler() {

    }

    public CommonInvocationHandler(Object target) {
        setTarget(target);
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        return method.invoke(target, args);
    }

}
