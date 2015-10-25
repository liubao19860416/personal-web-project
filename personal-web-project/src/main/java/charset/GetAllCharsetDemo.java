package charset;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

/**
 * 获取所有的字符集信息
 * 
 * @author Liubao
 * @2015年4月6日
 * 
 */
public class GetAllCharsetDemo {
    
    public static void main(String args[]) {
        SortedMap<String, Charset> all = null;
        all = Charset.availableCharsets(); // 得到全部可用的字符集
        Iterator<Map.Entry<String, Charset>> iter = null;
        iter = all.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Charset> me = iter.next();
            System.out.println(me.getKey() + " --> " + me.getValue());
        }
        
        
        System.out.println("============");
        for (Map.Entry entry : all.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key+"====>>"+value);
        }
        System.out.println(all.size());
        System.out.println(all.firstKey());
        System.out.println(all.lastKey());
        System.out.println(all.isEmpty());
//        all.clear();//不支持
        if(all!=null){
            System.out.println(all.isEmpty());
        }
        System.out.println(all.values());
    }
}