package time;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat.Field;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取日历格式数据
 * 
 * @author Liubao
 * @2014年12月2日
 * 
 */
public class CalendarDisply {
    
    
    public static void main(String[] args) throws IOException {
        Calendar ca = Calendar.getInstance();
        int MY_YEAR = 2014;
        int MY_MONTH = 1;
        int MY_DAY = 1;
        
        //标记位置
        begin1:
            
        System.out.print("请输入需要查询的年月日（yyyy-MM-dd）：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        String[] strNum = str.split("-");
        if (strNum.length == 0) {
            throw new RuntimeException("您输入的数据格式不正确！");
        }
        // 初始化默认日期为2015年1月1日；
        for (int i = 0; i < strNum.length; i++) {
            if (strNum.length == 1) {
                MY_YEAR = Integer.parseInt(strNum[0]);
            } else if (strNum.length == 2) {
                MY_YEAR = Integer.parseInt(strNum[0]);
                MY_MONTH = Integer.parseInt(strNum[1]);
            } else if (strNum.length == 3) {
                MY_YEAR = Integer.parseInt(strNum[0]);
                MY_MONTH = Integer.parseInt(strNum[1]);
                MY_DAY = Integer.parseInt(strNum[2]);
            } else {
                throw new RuntimeException("您输入的数据格式不正确！");
            }
        }

        ca.setTime(new Date());
        MY_DAY= ca.get(Calendar.DAY_OF_MONTH);
        ca.set(MY_YEAR, MY_MONTH - 1, MY_DAY);
        System.out.println("您输入的日期为：" + MY_YEAR + "年" + MY_MONTH + "月" );
        //System.out.println("您输入的日期为：" + MY_YEAR + "年" + MY_MONTH + "月" + MY_DAY + "日");
        
        String[] title = new String[] { "日", "一", "二", "三", "四", "五", "六" };
        int daysArray[][] = new int[6][7];

        // 设置输入的月份的对应的实际天数；
        // int daysInMonth = ca.get(Calendar.DAY_OF_MONTH);
        int daysInMonth = setDayInMonth(MY_YEAR, MY_MONTH);
        
        // 设置该天是这周的第几天；index的值是从1开始的，1对应星期日
        int index = ca.get(Calendar.DAY_OF_WEEK);

        // 输入制定格式的日期；
        setAllDays(daysArray, index, daysInMonth);

        System.out.println(MY_YEAR + "年" + MY_MONTH + "月的日历如下：");
        // 输出日历；
        printDate(title, daysArray);

    }

    /**
     * 在daysArray中拼装出当月的日期数据，index的值是从1开始的，1对应星期日
     */
    private static void setAllDays(int[][] daysArray, int index, int daysInMonth) {
        int days = 1;
        //设置起始那天的在日历中的第一行的位置的数据
        for (int i = index - 1; i < 7; i++) {
            daysArray[0][i] = days++;
        }
        //设置剩余的其他行数的数据信息
        for (int i = 1; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (days > daysInMonth) {
                    break;
                }
                daysArray[i][j] = days++;
            }
        }
    }

    /**
     * 打印日历格式
     */
    private static void printDate(String[] title, int[][] daysArray) {
        // System.out.println("日期为：" + ca.getTime());
        for (int i = 0; i < title.length; i++) {
            System.out.print(title[i] + "\t");
        }
        System.out.println();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (daysArray[i][j] == 0) {
                    if (i != 0) {
                        return;
                    }
                    System.out.print("\t");
                    continue;
                }
                System.out.print(daysArray[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 获取当月的天数是多少天
     */
    private static int setDayInMonth(int MY_YEAR, int MY_MONTH) {
        int daysInMonth = 31;
        switch (MY_MONTH) {
        case (1):
        case (3):
        case (5):
        case (7):
        case (8):
        case (10):
        case (12):
            daysInMonth = 31;
            break;
        case (4):
        case (6):
        case (9):
        case (11):
            daysInMonth = 30;
            break;
        case (2):
            if ((MY_YEAR % 100 != 0 && MY_YEAR % 4 == 0) || MY_YEAR % 400 == 0) {
                daysInMonth = 29;
                break;
            }
            daysInMonth = 28;
            break;
        }
        return daysInMonth;
    }
}
