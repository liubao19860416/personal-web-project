package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapOrList {

    // map转换成list
    public static List<Object> mapTransToList(Map<String, Object> map) {
        List<Object> list = new ArrayList<Object>();
        Iterator<?> iter = map.entrySet().iterator(); // 获得map的Iterator
        while (iter.hasNext()) {
            Entry<?, ?> entry = (Entry<?, ?>) iter.next();
            list.add(entry.getValue());
        }
        return list;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", "");
        map.put("2", "");
        List<?> list = mapTransToList(map);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
