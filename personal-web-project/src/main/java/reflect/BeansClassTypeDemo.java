package reflect;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

import base.entiy.User;

/**
 * 获取对象的方法的原始数据类型：现在调用有问题
 * 反射调用对应对象的对应属性的方法
 * 通过get方法，获取对应对对象的属性信息列表，但是会有一个class的属性存在；
 * 
 * @author Liubao
 * @2014年12月18日
 *
 */
public class BeansClassTypeDemo {

    // 公共jar包的属性
    private BeanInfo beanInfo;
    private BeanDescriptor beanDescriptor;
    private PropertyDescriptor propertyDescriptor[];

    /**
     * 获取对象的方法的原始数据类型：现在调用有问题
     */
    @Test
    public void getGenericRawType() throws Exception {
        Method method = User.class.getMethod("setUsername", String.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        //TODO 
        ParameterizedType ptype = (ParameterizedType) genericParameterTypes[0];
        System.out.println(ptype.getRawType());
        System.out.println(ptype.getActualTypeArguments()[0]);
    }

    /**
     * 反射调用对应对象的对应属性的方法
     */
    @Test
    public void PropertyDescriptorMethod() throws Exception {
        PropertyDescriptor ps = new PropertyDescriptor("username", User.class);
        User user = new User();

        // 反射获取对应的对象的读写方法
        Method readMethod = ps.getReadMethod();
        Method writeMethod = ps.getWriteMethod();
        writeMethod.invoke(user, "zhangsan");
        System.out.println(readMethod.invoke(user, null));

    }

    /**
     * 通过get方法，获取对应对对象的属性信息列表，但是会有一个class的属性存在；
     */
    @Test
    public void propertyDescriptorMethod() throws Exception {
        try {
            beanInfo = Introspector.getBeanInfo(User.class);
            //描述对应实体的信息的对象
            beanDescriptor = beanInfo.getBeanDescriptor();
            //描述对应实体的属性信息的对象
            propertyDescriptor = beanInfo.getPropertyDescriptors();
            System.out.println(beanDescriptor.getName());
            System.out.println(beanDescriptor.getDisplayName());
            // 获取对象的对应的属性
            for (PropertyDescriptor ps : propertyDescriptor) {
                System.out.println(ps.getName());
            }
            System.out
            .println("PropertyDescriptor长度为：" + propertyDescriptor.length);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
