package beancompare;


/**
 * 整理成了一个工具类，对两个字符串进行比较，返回0，1，-1值
 * 
 * @author Liubao
 * @2015年3月6日
 * 
 */
public class CompareVersion3 {
    /**
     * 
     * @param s1
     * @param s2
     * @return if s1>s2 return 1
     * @return if s1=s2 return 0
     * @return if s1<s2 return -1
     */
    public static int compare(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return 0;
        } else if (s1 == null) {
            return -1;
        } else if (s2 == null) {
            return 1;
        }

        /*
         * String[] arr1 = s1.split("[^a-zA-Z0-9]+"), arr2 = s2
         * .split("[^a-zA-Z0-9]+");
         */
        String[] arr1 = s1.split("[^a-zA-Z0-9]+");
        String[] arr2 = s2.split("[^a-zA-Z0-9]+");

        int i1, i2, i3;

        for (int ii = 0, max = Math.min(arr1.length, arr2.length); ii <= max; ii++) {
            if (ii == arr1.length) {
                return ii == arr2.length ? 0 : -1;
            } else if (ii == arr2.length) {
                return 1;
            }

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

            if (i3 != 0) {
                return i3;
            }
        }

        return 0;
    }

    public static void main(String[] ss) {

        String str1 = "2.0";
        String str2 = "1.5.1";
        System.out.println(CompareVersion3.compare(str1, str2) > 0);
        str1 = "1.5.1a";
        str2 = "1.5.1a";
        System.out.println(CompareVersion3.compare(str1, str2) == 0);
        str1 = "1.5.a123a";
        str2 = "1.5.a123b";
        System.out.println(CompareVersion3.compare(str1, str2) < 0);
        str1 = "a.5.a123A";
        str2 = "a.5.a123a";
        System.out.println(CompareVersion3.compare(str1, str2) < 0);
    }
}