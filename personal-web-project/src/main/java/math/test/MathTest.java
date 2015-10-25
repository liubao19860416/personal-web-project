package math.test;

import java.util.Scanner;
/**
 * 测试类
 * @author Liubao
 * @2014年12月2日
 *
 */
public class MathTest {
    
    /**
     * 获取的制定规则的奇数的个数
     */
    public static void getOddNumber() {
        Scanner s = new Scanner(System.in);
        int i = 7, j = 0;// 组成的奇数的最高位数（长度）
        int sum1 = 4, sum2 = sum1 * 7;
        int sum = sum2;
        while (true) {
            // if (s.next().equals("over")) {
            // break;
            // }
            System.out.println("请您输入奇数的最高位数：");
            if (s.hasNext()) {
                i = s.nextInt();
                // System.out.println("请您输入数字的取值范围（0-10）：");
                // j = s.nextInt();
                // System.out.println("您输入数字的取值范围为：" + j);
            }
            if (!(i < 10 && i > 0)) {
                System.out.println("请重新输入1--9之间的数字！！！");
                continue;
            }
            System.out.println("您输入的奇数的最高位数为：" + i);
            break;
        }
        for (int k = 3; k <= i; k++) {
            sum = sum * 8;
            System.out.println("0-" + i + "组成" + k + "位数的奇数有" + sum + "个！！！");
        }

    }
}
