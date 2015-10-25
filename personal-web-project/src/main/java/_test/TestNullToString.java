package _test;

import java.util.HashMap;
import java.util.Map;
/**
 * 测试空null的强制转换
 * 
 * @author admin
 *
 */
public class TestNullToString {
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key1", "value1");
		String str = (String) map.get("key");
		String str2=null;
		System.out.println((String)str2);
		System.out.println(str);
		
	}
}
