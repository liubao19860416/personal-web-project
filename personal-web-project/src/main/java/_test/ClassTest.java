package _test;



/**
 * Class相关的一些测试方法:枚举，接口，匿名类，数组，实例，基本数据类型
 * 
 * @author Liubao
 * @2015年1月3日
 * 
 */
public class ClassTest {

    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        Class clazz = ClassTest.class;

        //注解类型判断
        System.out.println(clazz.isAnnotation());
        System.out.println(clazz.isAnonymousClass());
        
        //数序，枚举类型判断
        System.out.println(clazz.isArray());
        System.out.println(clazz.isEnum());
        
        //基本数据类型
        System.out.println(clazz.isPrimitive());
        System.out.println(clazz.isInterface());
        
        //实例类型判断
        System.out.println(clazz.isInstance(null));
        System.out.println(clazz.isInstance(new ClassTest()));
    }

}
