package beancompare;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 前面的简单版本号递归算法并不算好 下面我们看一个复杂点，但非常实用的版本号比较类
 * 
 * @author Liubao
 * @2015年3月6日
 * 
 */
public class CompareVersion2 {
    private static class VersionStringComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            if (s1 == null && s2 == null)
                return 0;
            else if (s1 == null)
                return -1;
            else if (s2 == null)
                return 1;

            String[] arr1 = s1.split("[^a-zA-Z0-9]+"), arr2 = s2
                    .split("[^a-zA-Z0-9]+");

            int i1, i2, i3;

            for (int ii = 0, max = Math.min(arr1.length, arr2.length); ii <= max; ii++) {
                if (ii == arr1.length)
                    return ii == arr2.length ? 0 : -1;
                else if (ii == arr2.length)
                    return 1;

                try {
                    i1 = Integer.parseInt(arr1[ii]);
                } catch (Exception x) {
                    i1 = Integer.MAX_VALUE;
                }

                try {
                    i2 = Integer.parseInt(arr2[ii]);
                } catch (Exception x) {
                    i2 = Integer.MAX_VALUE;
                }

                if (i1 != i2) {
                    return i1 - i2;
                }

                i3 = arr1[ii].compareTo(arr2[ii]);

                if (i3 != 0)
                    return i3;
            }

            return 0;
        }
    }

    public static void main(String[] ss) {

        String[] data = new String[] { "2.0", "1.5.1", "10.1.2.0", "9.0.0.0",
                "2.0.0.16", "1.6.0_07", "1.6.0_07-b06", "1.6.0_6",
                "1.6.0_07-b07", "1.6.0_08-a06", "5.10", "Generic_127127-11",
                "Generic_127127-13" };

        List<String> list = Arrays.asList(data);
        Collections.sort(list, new VersionStringComparator());

        for (String s : list)
            System.out.println(s);
    }
}