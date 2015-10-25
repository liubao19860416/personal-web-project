package proxy.reflection;

import java.lang.reflect.Proxy;

/**
 * 动态代理：代理对象和被代理对象必须事项相同的接口信息
 * 
 * @author Liubao
 * @2015年3月16日
 * 
 */
public class RunDebugTest {
    
    public static void main(String[] args) {
        try {
            // Create the real interface implementation
            TestInterface target = new TestTarget();

            // Create a proxy to wrap the original implementation
            DebugProxy proxy = new DebugProxy(target);

            // Get a reference to the proxy through the TestInterface interface
            TestInterface test = (TestInterface) Proxy.newProxyInstance(
                    TestInterface.class.getClassLoader(),
                    new Class[] { TestInterface.class }, proxy);

            // Invoke some methods on the test interface
            System.out.println(test.doTest1("This is test ", 1));
            test.doTest2(new String[] { "foo", "bar", "baz" });
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
