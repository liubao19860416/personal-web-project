package array;

import java.lang.reflect.Field;
/**
 * 对象类型判断工具类
 * 
 * @author Liubao
 * @2014年12月2日
 *
 */
public class ObjectTypeUtils {
    
    public static void main(String[] args) {
        double dou = 0.34;
        String str = "你好";
        int[] array = { 3, 5, 3, 7, 8 };
        String[] strs = { "N", "HAO", "nihao好"};
        Thread t = new Thread();

        System.out.println(getBasicObjectType(dou));
        System.out.println(getBasicObjectType(str));
        System.out.println(getBasicObjectType(array));
        System.out.println(getBasicObjectType(strs));
        System.out.println(getBasicObjectType(t));
        
        System.out.println(getCustomerObjectTypeSimpleName(dou));
        System.out.println(getCustomerObjectTypeSimpleName(str));
        System.out.println(getCustomerObjectTypeSimpleName(array));
        System.out.println(getCustomerObjectTypeSimpleName(strs));
        System.out.println(getCustomerObjectTypeSimpleName(t));
        
    }

    /**
     * 可以返回数据类型的全称；可以对该对象中的每一个属性进行判断
     */
    @SuppressWarnings("unused")
    private static String getCustomerObjectType(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        if (fields[0].getType().getSimpleName().equals("int")
                && fields[1].getType().getSimpleName().equals("String")) {
            return obj.getClass().getSimpleName() + "["
                    + fields[0].getType().getSimpleName() + ","
                    + fields[1].getType().getSimpleName() + "]";
        }
        return null;
    }

    /**
     * 推荐使用；可以返回基本数据类型名字简称；
     */
    private static String getCustomerObjectTypeSimpleName(Object obj) {
        return obj.getClass().getSimpleName();
    }
    
    /**
     * 推荐使用；返回数组类型对象的Class，不是数组的则返回null；
     */
    @SuppressWarnings("unused")
    private static Class getObjectArrayClassType(Object obj) {
        if (obj.getClass().isArray()) {
            return obj.getClass().getComponentType();
        } else {
            return null;
        }
    }

    /**
     * java里没有typerof ,要用instanceof,而且基础类型行,要用对应的对象；同上；
     * 返回指定的基本数据对应的类型名称；
     */
    private static String getBasicObjectType(Object obj) {
        if (obj instanceof Integer) {
            return "int";
        }
        if (obj instanceof Float) {
            return "float";
        }
        if (obj instanceof Character) {
            return "char";
        }
        if (obj instanceof Double) {
            return "double";
        }
        if (obj instanceof String) {
            return "String";
        }
        if (obj instanceof Boolean) {
            return "boolean";
        }
        if (obj instanceof Byte) {
            return "byte";
        }
        if (obj instanceof int[]) {
            return "int[]";
        }
        if (obj instanceof int[]) {
            return "int[]";
        }
        if (obj.getClass().isArray()) {
            return "您输入的是数组数据类型！"+obj.getClass();
        }
        return "您输入的不是基本数据类型(包括数组)！";
    }
}
