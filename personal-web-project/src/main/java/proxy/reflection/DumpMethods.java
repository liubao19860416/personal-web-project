package proxy.reflection;

import java.lang.reflect.Method;

/**
 * 可以导出对应的类的下面的所有的方法名等信息
 * 
 * @author Liubao
 * @2015年3月16日
 * 
 */
public class DumpMethods {

    public static void main(String args[]) throws Exception {
        DumpMethods.dumpMethods("java.lang.String");
        System.out.println("================");
        DumpMethods.dumpMethods("java.lang.Object");
        System.out.println("================");
        DumpMethods.dumpMethods("proxy.reflection.Customer");
    }
    
    
    public static void dumpMethods(String arg) throws Exception {
        Class<?> classType = Class.forName(arg);
        Method methods[] = classType.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i].toString());
        }
    }
}
