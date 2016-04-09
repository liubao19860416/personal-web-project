package reflect;

import java.lang.reflect.Method;

import org.junit.Test;

import _test.Person;

//import com.saike.grape.base.BaseEnvironment;

/**
 * 获取类加载器（3种类型Bootstrap ClassLoader、Extension ClassLoader、Application
 * ClassLoader和User Defined ClassLoader）
 * 
 * @author Liubao
 * @2015年4月6日
 * 
 */
public class ClassLoaderDemo {

    /**
     * sun.misc.Launcher$AppClassLoader
     * @throws Exception
     */
    @Test
    public void testName1() throws Exception {
        Person stu = new Person();
        System.out.println("类加载器1："
                + stu.getClass().getClassLoader().getClass().getName());
//        System.out.println("类加载器1："
//                + BaseEnvironment.class.getClassLoader().getClass().getName());
        System.out.println("类加载器1："
                +  new Thread().getContextClassLoader().getClass().getName());
    }

    /**
     * 获取上级类加载器:需要注意的是这里并没有输出引导类加载器，这是由于有些 JDK
     * 的实现对于父类加载器是引导类加载器的情况，getParent()方法返回 null
     * 
     *  获取上级类加载器
     *  sun.misc.Launcher$ExtClassLoader
     *  sun.misc.Launcher$AppClassLoader
     */
    @Test
    public void testName2() throws Exception {
        ClassLoader loader = ClassLoaderDemo.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.getClass().getName());
            loader = loader.getParent();
        }

    }

    @Test
    public void testName3() throws Exception {
        MyClassLoader loader1 = new MyClassLoader();
        MyClassLoader loader2 = new MyClassLoader();
        Class class1 = loader1.loadClass("_test.Son");
        Class class2 = loader2.loadClass("_test.Son");
        Object obj1 = class1.newInstance();
        Object obj2 = class2.newInstance();
        
        ClassLoader loader3 = ClassLoaderDemo.class.getClassLoader();
        Object obj3 = loader3.loadClass("reflect.ClassLoaderDemo");
        
        //_test.Son默认是由系统的类加载加载的
        //由于是由两个类加载器加载的类，所以认为不是一个相同的类型，虽然他们的名字是相同的
        System.out.println(obj1 instanceof _test.Son);//true
        System.out.println(obj2 instanceof _test.Son);//true
        System.out.println(obj3 instanceof reflect.ClassLoaderDemo);//false
        
        Method method = class1.getMethod("setAge", java.lang.String.class);
        method.invoke(obj1, "22");
        method = class2.getMethod("setAge", java.lang.String.class);
        method.invoke(obj2, "22");
    }

};