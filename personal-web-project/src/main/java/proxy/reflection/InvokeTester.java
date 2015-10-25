package proxy.reflection;

import java.lang.reflect.Method;

public class InvokeTester {
    public int add(int param1, int param2) {
        return param1 + param2;
    }

    public String echo(String msg) {
        return "echo: " + msg;
    }

    public static void main(String[] args) throws Exception {
        Class<?> classType = InvokeTester.class;
        Object invokeTester = classType.newInstance();

        // Object invokeTester = classType.getConstructor(new
        // Class[]{}).newInstance(new Object[]{});

        Method addMethod = classType.getMethod("add", new Class[] { int.class,
                int.class });
        Object result = addMethod.invoke(invokeTester, new Object[] {
                new Integer(100), new Integer(200) });
        System.out.println((Integer) result);

        Method echoMethod = classType.getMethod("echo",
                new Class[] { String.class });
        result = echoMethod.invoke(invokeTester, new Object[] { "Hello" });
        System.out.println((String) result);
    }
}
