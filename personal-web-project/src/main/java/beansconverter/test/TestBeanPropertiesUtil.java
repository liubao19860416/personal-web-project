package beansconverter.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import time.DatetimeUtils;
import _test.Person;
import base.entiy.User;
import beansconverter.BeanPropertiesUtil;
/**
 * 测试获取对象属性工具类
 * 
 * @author admin
 *
 */
public class TestBeanPropertiesUtil {

    @Test
    public void test01() throws Exception {
        Person person = new Person();
        person.setPid(3l);
        person.setBirthday(DatetimeUtils.currentTimestamp());
        person.setDescription("描述信息");
        person.setPassword("123456");
        person.setPname("name");
        
        User user=new User();
        BeanPropertiesUtil.copyProperties(user, person);
        
        System.out.println(user);
    }
    
    @Test
    public void test02() throws Exception {
        List<Object> objList = new ArrayList<Object>();
        for (int i = 0; i <10; i++) {
            Person person = new Person();
            person.setPid(3l+i);
            person.setBirthday(DatetimeUtils.currentTimestamp());
            person.setDescription("描述信息"+i);
            person.setPassword("123456"+i);
            person.setPname("name"+i);
            objList.add(person);
        }
        List<Object> list = BeanPropertiesUtil.getBeanProperty2List(objList, "pid");
        System.out.println(list.toString());
        
        //可变参数，可以使用数组代替
        String[]  strs=new String[]{"pid","pname","birthday"};
        Map<String, List<Object>> list2 = BeanPropertiesUtil.getBeanProperties2Map(objList,strs);
        System.out.println(list2.toString());
    }
    
    @Test
    public void test03() throws Exception {
        Person person = new Person();
        person.setPid(3l );
        person.setBirthday(DatetimeUtils.currentTimestamp());
        person.setDescription("描述信息" );
        person.setPassword("123456" );
        person.setPname("name" );
        Object dest= new ArrayList<Object>();
        List<Object> objList = new ArrayList<Object>();
        BeanPropertiesUtil.copyPropertiesNotNulFromMapToObject(dest, person);
        BeanPropertiesUtil.copyPropertiesNotNulFromMapToObject(objList, person);
        System.out.println(dest.toString());
        System.out.println(objList.toString());
    }

}
