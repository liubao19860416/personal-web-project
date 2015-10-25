package _test;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;
import java.util.WeakHashMap;

import com.meidusa.fastjson.JSON;


public class TestMany {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("liubao" + i);
        }
        //测试对List进行迭代取出元素的操作
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
           String result = it.next();
        }
        // 这样可以在迭代过程中，进行删除等操作，且可以进行双向操作
        ListIterator<String> it2 = list.listIterator();
        while (it2.hasNext()) {
            String result = it2.next();
        }
        // 弱引用
        Map<String, String> weakHashMap = new WeakHashMap<String, String>();
        weakHashMap.put("id", "001");
        weakHashMap.put("name", "liubao");
        weakHashMap.put("password", "1111");
        System.out.println(JSON.toJSONString(weakHashMap));
        // 运行gc程序后，弱引用对象中的数据就会被清空！
        System.gc();
        // 判断是否有值，然后输出该值
        System.out.println(weakHashMap);
        
        //Vector的特有的操作迭代输出方法
        Enumeration enum1 = new Vector().elements();
    }
}
