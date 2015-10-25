package imagefilter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取指定网页的email和QQ号码等指定信息
 * @author Liubao
 * @2014年12月3日
 *
 */
public class EmailAndPhonesSpider {

    public static void main(String[] args) {
        String urlName="http://programme.tvb.com/drama/swipetaplove";
        String destFilePath="D:/temp/emails.txt";

        getPhoneNumsOfTreeSet(urlName, destFilePath);
        
        getMailsOfTreeSet(urlName, destFilePath);

    }

    private static void getPhoneNumsOfTreeSet(String urlName,String destFilePath) {
       
        //自己复写比较器，进行制定规则排序
        TreeSet<String> ts = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                } else {
                    return o1.length() - o2.length();
                }
            }
        });

        //连接信息
        File destFile = new File(destFilePath);
        URL url = null;
        BufferedWriter bufw = null;
        try {
            url = new URL(urlName);
            //将从url中读取的数据指定为系统默认输入流
            System.setIn(url.openStream());// openConnection().getInputStream();
            bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFile)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

        //进行数据的正则规则校验
        String line = null;
        // String phone_regex = "[1][34568][0-9]{9}"; 
        String qq_regex = "[1-9][0-9]{4,10}"; 
        Pattern p = Pattern.compile(qq_regex);
        Matcher m = null;
        int count = 0;
        try {
            while ((line = bufr.readLine()) != null) {
                if (line.contains("QQ") || line.contains("qq")) { 
                    m = p.matcher(line);
                    if (m.find()) {
                        count++;
                        ts.add(m.group());
                    }
                }
            }
            bufw.newLine();
            bufw.flush();

            // count=ts.size();
            count = 0;
            for (Iterator<String> it = ts.iterator(); it.hasNext();) {
                bufw.write("QQ " + ++count + "\t:" + "" + it.next());
                bufw.newLine();
                bufw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void getMailsOfTreeSet(String urlName,String destFilePath) {

        URL url = null;
        BufferedWriter bufw = null; 
        File destFile = new File(destFilePath);
        TreeSet<String> ts = new TreeSet<String>();
        try {
            url = new URL(urlName);
            System.setIn(url.openStream());// openConnection().getInputStream();
            bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFile)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        String mail_regex = "[a-zA-Z_][a-zA-Z0-9_]{5,15}@[a-zA-Z0-9_]+(\\.[a-zA-Z]{1,3})+";
        Pattern p = Pattern.compile(mail_regex);
        Matcher m = null;
        try {
            while ((line = bufr.readLine()) != null) {
                m = p.matcher(line);
                if (m.find()) {
                    System.out.println(m.group());
                    ts.add(m.group());
                }
            }

            for (Iterator<String> it = ts.iterator(); it.hasNext();) {
                bufw.write(it.next());
                bufw.newLine();
                bufw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
