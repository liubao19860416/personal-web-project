package base.enumer;

import java.util.EnumSet;

import org.junit.Test;

enum Color1 {
    RED, GREEN, BLUE;
}

/**
 * EnumSet测试类
 * 
 * @author Liubao
 * @2015年3月23日
 * 
 */
public class EnumSetDemo0 {

    @Test
    public void test01() throws Exception {
        EnumSet<Color1> es = null;
        System.out.println("======== EnumSet.allOf(Color.class) =====");
        es = EnumSet.allOf(Color1.class);
        print(es);
    }

    @Test
    public void test02() throws Exception {
        EnumSet<Color1> es = null;
        System.out.println("======== EnumSet.of(Color.BLUE) =====");
        es = EnumSet.of(Color1.BLUE);
        print(es);
    }

    @Test
    public void test03() throws Exception {
        EnumSet<Color1> es = null;
        System.out.println("======== EnumSet.noneOf(Color.class) =====");
        es = EnumSet.noneOf(Color1.class);
        es.add(Color1.RED);
        es.add(Color1.GREEN);
        print(es);
    }

    @Test
    public void test04() throws Exception {
        EnumSet<Color1> esOld = null;
        EnumSet<Color1> esNew = null;
        System.out.println("======== EnumSet.complementOf(Color.class) =====");
        esOld = EnumSet.noneOf(Color1.class);
        esOld.add(Color1.RED);
        esOld.add(Color1.GREEN);
        esNew = EnumSet.complementOf(esOld);
        print(esNew);
    }

    @Test
    public void test05() throws Exception {
        EnumSet<Color1> esOld = null;
        EnumSet<Color1> esNew = null;
        System.out.println("======== EnumSet.copyOf(Color.class) =====");
        esOld = EnumSet.noneOf(Color1.class);
        esOld.add(Color1.RED);
        esOld.add(Color1.GREEN);
        esNew = EnumSet.copyOf(esOld);
        print(esNew);
    }

    public static void print(EnumSet<Color1> temp) {
        for (Color1 c : temp) {
            System.out.print(c + ",");
        }
        System.out.println();
    }
};