package math.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
/**
 * 使用Scanner对输入的字符串格式进行正则校验
 * 
 * @author Liubao
 * @2014年12月2日
 *
 */
public class ScannerRegexTest{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = null;
        while (sc.hasNext()) {
            str = sc.next("\\d{4}-\\d{2}-\\d{2}");
            if (str != null) {
                try {
                    System.out.println("str:"
                            + new SimpleDateFormat("yyyy-MM-dd")
                                    .format(new SimpleDateFormat("yyyy-MM-dd")
                                            .parse(str)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
