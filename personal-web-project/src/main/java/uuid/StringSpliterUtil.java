package uuid;

import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 使用delim将str字符串拆分的结果字符串的个数或者数组结果
 * @author Liubao
 * @2014年12月10日
 *
 */
public class StringSpliterUtil {

    public static void main(String[] args) {
        int count = getCount("1-11-1", "-");
        System.out.println(count);
        
        System.out.println("111".indexOf("."));
        System.out.println(count);
        
        String[] array = getArray("1-11-1", "-");
        System.out.println(Arrays.toString(array));
    }

    /**
     * 使用delim将str字符串拆分的结果字符串的个数
     */
    public static int getCount(String str, String delim) {
        if (str == null||str.indexOf(delim)<0) {
            return 0;
        }
        StringTokenizer s = new StringTokenizer(str, delim);
        return s.countTokens();
    }

    /**
     * 使用delim将str字符串拆分的结果字符串的数组
     */
    public static String[] getArray(String str, String delim) {
        int count = getCount(str, delim);
        int j = 0;
        String[] arr = new String[count];
        for (int i = 0; i < count; i++) {
            if (str.indexOf(delim) != -1) {
                j = str.indexOf(delim);
                arr[i] = str.substring(0, j);
                str = str.substring(j + 1);
            } else {
                arr[i] = str;
            }
        }
        return arr;
    }

}
