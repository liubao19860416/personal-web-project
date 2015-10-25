package array;

import java.lang.reflect.Array;

/**
 * 反射方法，拷贝被生成一个新数组
 * 
 * @author Liubao
 * @2015年4月6日
 * 
 */
public class ReflectCopyArrayUtil {

    public static void main(String args[]) throws Exception {
        int temp[] = { 1, 2, 3 };// 声明一整型数组
        int newTemp[] = (int[]) arrayCopy(temp, 5); // 重新开辟空间5
        printArray(newTemp);
        
        System.out.println("\n-------------------------");
        String t[] = { "lxh", "mldn", "mldnjava" };
        String nt[] = (String[]) arrayCopy(t, 8);
        printArray(nt);
    }

    public static Object arrayCopy(Object obj, int len) {
        Class<?> c = obj.getClass();
        Class<?> arr = c.getComponentType(); // 得到数组的
        Object newArray =  Array.newInstance(arr, len); // 开辟新的大小
        int co = Array.getLength(obj);
        System.arraycopy(obj, 0, newArray, 0, co); // 拷贝内容
        return newArray;
    }

    // 数组输出
    public static void printArray(Object obj) { 
        Class<?> c = obj.getClass();
        if (!c.isArray()) { // 判断是否是数组
            return;
        }
        Class<?> arr = c.getComponentType();
        System.out.println(arr.getName() + "数组的长度是：" + Array.getLength(obj)); // 输出数组信息
        for (int i = 0; i < Array.getLength(obj); i++) {
            System.out.print(Array.get(obj, i) + "、"); // 通过Array输出
        }
    }
};