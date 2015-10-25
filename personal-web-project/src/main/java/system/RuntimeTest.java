package system;

import java.io.IOException;
/**
 * Runtime相关属性方法测试
 * @author Liubao
 * @2014年12月2日
 *
 */
public class RuntimeTest {

    public static void main(String[] args) throws IOException {
        Runtime rt = Runtime.getRuntime();
        System.out.println(rt.maxMemory() / 1024f / 1024f);
        System.out.println(rt.availableProcessors());
        System.out.println(rt.freeMemory());
        System.out.println(rt.maxMemory());
        System.out.println(rt.totalMemory());
        System.out.println(rt.exec("C:\\Windows\\notepad.exe"));
        //默认路径 OK
        Runtime.getRuntime().exec("notepad.exe");

    }

}
