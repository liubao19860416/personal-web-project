package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

public class ReflectTest {
    @Test
    public void test01() throws Exception {
        Class clazz = Class.forName("itcast.reflect.Student");

        // 获取有参/无参数的构造方法；然后创建对象，操作对象属性；
        // 获取无参数的构造方法；然后创建对象，操作对象属性；
        Constructor[] constructors = clazz.getConstructors();
        Constructor constructor1 = clazz.getConstructor(String.class, int.class);
        Constructor constructor2 = clazz.getConstructor(null);


        // 获取main方法
        Method method11 = clazz.getMethod("main", String[].class);
        // method11.invoke(null, new String[]{"zhangsan","lisi"});//错误的；
        method11.invoke(null,new Object[] { new String[] { "zhangsan", "lisi" } });// 正确的；

        // 获取普通私有方法；
        Method method00 = clazz.getDeclaredMethod("toLocalString", null);
        method00.setAccessible(true);
    }
}
