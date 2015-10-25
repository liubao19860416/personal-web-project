package regex;

import java.lang.reflect.Field;

import base.entiy.User;

/**
 * 反射工具类,可以获取或者设置对应对象的属性值
 * 
 * @author Liubao
 * @2014年12月3日
 * 
 */
public class ReflectUtil {
    
    //测试
    public static void main(String[] args) {
        String fieldName="username";
        User obj=new User();
        obj.setUsername("LIUBAO");
        
        //私有方法测试
        Field field = ReflectUtil.getField(obj, fieldName);
        System.out.println(field.getName());
        
        //获取该字段Field的原始类型
        System.out.println(field.getGenericType());
        
        //修改前获取
        Object value = ReflectUtil.getFieldValue(obj, fieldName);
        System.out.println(value);
        
        //设置修改
        ReflectUtil.setFieldValue(obj, fieldName, "刘保");
        value = ReflectUtil.getFieldValue(obj, fieldName);
        System.out.println(value);
        
    }
    
    /**
     * 利用反射获取指定对象的指定属性
     * @param obj 目标对象
     * @param fieldName 目标属性
     * @return 目标属性的值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 利用反射设置指定对象的指定属性为指定的值
     * 
     * @param obj  目标对象
     * @param fieldName 目标属性
     * @param fieldValue 目标值
     */
    public static void setFieldValue(Object obj, String fieldName,String fieldValue) {
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 利用反射获取指定对象里面的指定属性
     * 
     * @param obj
     *            目标对象
     * @param fieldName
     *            目标属性
     * @return 目标字段
     */
    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        //循环获取当前对象的父类对象的类型信息，直到两者为统一对象的时候退出
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                // 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
            }
        }
        return field;
    }
}
