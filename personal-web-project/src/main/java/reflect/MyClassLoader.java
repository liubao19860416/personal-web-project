package reflect;

import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义类加载器
 * 两个类加载器加载同一个class会得到不同的类，所以重写loadClass方法，如果只重写findClass方法，会由双亲来加载class文件
 * ，得不到想到的效果。
 * 
 * @author Liubao
 * @2015年4月6日
 * 
 */
public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        InputStream is = getClass().getResourceAsStream(fileName);
        if (is == null) {
            return super.loadClass(name);
        }
        try {
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.loadClass(name);
    }

}