package proxy.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 对象进行反射获取get和set方法
 * 
 * @author Liubao
 * @2015年3月16日
 * 
 */
public class ReflectTester {

    public Object copy(Object object) throws Exception {
        Class<?> classType = object.getClass();
        System.out.println("Class:" + classType.getName());

        // 通过获取空参数的构造方法，创建的对象
        Object objectCopy = classType.getConstructor(new Class[] {})
                .newInstance(new Object[] {});

        // 获取声明的字段属性信息
        Field fields[] = classType.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            // 获取对应的set和get方法
            String fieldName = field.getName();
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getMethodName = "get" + firstLetter + fieldName.substring(1);
            String setMethodName = "set" + firstLetter + fieldName.substring(1);

            // 构造对应的get和set方法
            Method getMethod = classType.getMethod(getMethodName,
                    new Class[] {});
            Method setMethod = classType.getMethod(setMethodName,
                    new Class[] { field.getType() });

            // 执行对应的invoke方法
            Object value = getMethod.invoke(object, new Object[] {});
            System.out.println(fieldName + ":" + value);
            setMethod.invoke(objectCopy, new Object[] { value });
        }
        return objectCopy;
    }

    // 测试main方法
    public static void main(String[] args) throws Exception {
        
        Customer customer = new Customer("Tom", 21);
        customer.setId(new Long(1));

        Customer customerCopy = (Customer) new ReflectTester().copy(customer);
        System.out.println("Copy information:" + customerCopy.getId() + " "
                + customerCopy.getName() + " " + customerCopy.getAge());
    }
}

class Customer {
    private Long id;

    private String name;

    private int age;

    public Customer() {
    }

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
