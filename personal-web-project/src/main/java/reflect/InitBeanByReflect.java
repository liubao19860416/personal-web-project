package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import base.entiy.User;

public class InitBeanByReflect {
    
    public static void main(String[] args) throws Exception {
        Map <String,String> param=new HashMap<String, String>();
//        initBean(param,User.class);
        initBean(User.class);
    }
    
    /**
     * @param clazz
     * @throws Exception
     */
    public static <T> void initBean(Map<String,String> param ,Class<T> clazz) throws Exception{
        T newInstance = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if("serialVersionUID".equalsIgnoreCase(name)){
                continue;
            }
            System.out.println(name);
        }
        System.out.println("====================================");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
//            method.invoke(obj, args);
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            System.out.println(name);
        }
    }
    
    public static <T> void initBean(Class<T> clazz) throws Exception{
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if("serialVersionUID".equalsIgnoreCase(name)){
                continue;
            }
            System.out.println(name);
        }
        System.out.println("====================================");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
//            method.invoke(obj, args);
            String name = method.getName();
            System.out.println(name);
        }
    }

}
