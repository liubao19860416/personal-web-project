package base.enumer;

import java.util.EnumMap;
import java.util.Map;

enum Color {
    RED, GREEN, BLUE;
}

/**
 * 测试集合EnumMap的使用
 * 
 * @author Liubao
 * @2015年3月23日
 * 
 */
public class EnumMapDemo {
    public static void main(String args[]) {
        Map<Color, String> enumMap = new EnumMap<Color, String>(Color.class);
        enumMap.put(Color.RED, "红色");
        enumMap.put(Color.GREEN, "绿色");
        enumMap.put(Color.BLUE, "黄色");
        for (Color c : Color.values()) {
            System.out.println(c.name() + " --> " + enumMap.get(c));
        }
        for (Color c : enumMap.keySet()) {
            System.out.print(c.name() + "，");
        }
        System.out.println();
        for (String s : enumMap.values()) {
            System.out.print(s + "，");
        }
    }
};