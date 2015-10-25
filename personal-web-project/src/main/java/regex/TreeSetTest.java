package regex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

/**
 * TreeSet和正则表达式相关测试
 * 
 * @author Liubao
 * @2014年12月2日
 * 
 */
public class TreeSetTest {

    public static void main(String[] args) {
        String[] ips = { "192.168.10.34", "110.110.1.1", "127.0.0.1",
                "3.3.3.3", "105.70.11.55" };
        TreeSet<String> ts = new TreeSet<String>();
        // 填补位数为3位
        for (int i = 0; i < ips.length; i++) {
            ips[i] = ips[i].replaceAll("(\\d+)", "00$1");
            ips[i] = ips[i].replaceAll("0*(\\d{3})", "$1");
            // ts.add(ips[i]);
        }
        // 添加到TreeSet集合中
        ts.addAll(Arrays.asList(ips));

        System.out.println("=======String[] ips==============");
        for (String ip : ips) {
            System.out.println("[" + ip + "]");
        }

        System.out.println("=======TreeSet<String> ts==============");
        for (String ip : ts) {
            System.out.println("[" + ip + "]");
        }

        System.out.println("=======ts.iterator()==============");
        int index = 0;
        for (Iterator<String> it = ts.iterator(); it.hasNext();) {
            ips[index] = it.next().replaceAll("0*(\\d+)", "$1");
            System.out.println("[" + ips[index] + "]");
            index++;
        }
        
        //这种方式不太容易记忆
        Map<String, Object> map=new  HashMap<String, Object>();
        Set keySet = map.keySet();
        for (Iterator<Entry<String, Object>> it = keySet.iterator(); it.hasNext();) {
            Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            System.out.println(entry.getKey() + "---->" + entry.getValue());

        }

    }
}
