package proxy;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * jdk 1.6之上提供的工具类:ToolProvider
 * 
 * @author Liubao
 * @2015年5月3日
 * 
 */
public class JDKTest {
    
    public static void main(String[] args) {

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        ClassLoader classLoader = ToolProvider.getSystemToolClassLoader();
        System.out.println(javaCompiler.getSourceVersions());
        System.out.println(javaCompiler.getClass().getCanonicalName());
        System.out.println(javaCompiler.getClass().getName());
        System.out.println("========================");
        System.out.println(classLoader.getClass().getName());
    }
    
}
