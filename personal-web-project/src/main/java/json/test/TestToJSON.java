package json.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import _test.Person;
import _test.Son;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestToJSON {

	/**
	 * 测试map和json格式的数据互相转换
	 * 
	 */
	@Test
	public void TestMapToJSON() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < 10; i++) {
			map.put("key" + i, i);
		}
		String str = JSONObject.toJSONString(map);

		System.out.println(str);
	}
	
	@Test
	@SuppressWarnings("all")
	public void TestJSONToMap() {
	    Map<String, Object> map = new HashMap<String, Object>();
	    for (int i = 0; i < 10; i++) {
	        map.put("key" + i, i);
	    }
	    String str1 = JSONObject.toJSONString(map);
	    map = JSONObject.parseObject("{key:123}", Map.class);
	    map.clear();
	    
	    map = JSON.parseObject("{'key':123}", Map.class);
	    map.clear();
	    String str2="{\"key4\":4,\"key3\":3,\"key6\":6,\"key5\":5,\"key0\":0,\"key2\":2,\"key1\":1,\"key8\":8,\"key7\":7,\"key9\":9}";
	    map = JSON.parseObject(str1, Map.class);
	    map.clear();
	    System.out.println(str1+":"+str2);
	}
	
	
	@Test
	public void TestPersonToJSON() {
		String str = "";
		for (int i = 0; i < 10; i++) {
			Person person=new Person(10000l+i, "pname"+i, "password"+i, new Date());
			Son son = new Son();
            son.setAge("18"+i);
            son.setName("name"+i);
            person.setSon(son);
		 str += JSONObject.toJSONString(person);
		}
		
		System.out.println(str);
	}

	@Test
	public void TestJSONToPerson() {
	    String str = "";
	    for (int i = 0; i < 1; i++) {
	        Person person=new Person(10000l+i, "pname"+i, "password"+i, new Date());
	        Son son = new Son();
            son.setAge("18");
            son.setName("name"+i);
            person.setSon(son);
	        str = JSONObject.toJSONString(person);
	    }
	    Person person = JSONObject.parseObject(str, Person.class);
	    System.out.println(person);
	    person = JSON.parseObject(str, Person.class);
	    System.out.println(person);
	}
}
