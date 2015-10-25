package beansconverter.test;

import java.lang.reflect.Field;

import org.junit.Test;

import _test.Person;

/**
 * 测试 获取对象的所有属性
 * 
 * @author Liubao
 */
public class TestBeanProperties {

    @Test
    public void testName() throws Exception {
        Field[] fields1 = Person.class.getDeclaredFields();
        //Person person = new Person();
        //Field[] fields2 = person.getClass().getDeclaredFields();
        Field.setAccessible(fields1, true);
        int length1 = fields1.length;
        String str = "";
        for (Field field : fields1) {
            str += field.getName() + ",";
        }
        str = str.substring(0, str.length() - 1);
        System.out.println("["+str+"]"+length1);
    }
}
