package regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;


public class RegexTest {
    
    
    private static void test000( ) {
        String str = "dsa ssf ff fsfdf dds sfs wefsr wr2 345";
        String str1 = "dsa";
        String regex = "\\b[a-z]{3}\\b";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        boolean b = m.matches(); 
        Matcher m1 = p.matcher(str1);
        boolean b1 = m1.matches();
        System.out.println(b + ":" + b1); 

        m.reset();
        while (m.find()) {
            System.out.println(m.group());
        }
    }
    
    private static boolean checkEmail(String str) {
        String regex="@163.com";
        String[] strs=str.split(regex);
        str.replaceAll(regex, str);
        for(String s:strs)
        {
            System.out.println(s+",");
        }
        System.out.println("****************************");
        return false;
    }
    
    private static void replaceAll() {
        String str="ajdasfjsa;ljd;lajfdglsla;lfjputr385-08oiu[prw\\ q]fa";
        String regex="";
        str=str.replaceAll(regex, str);
        System.out.println(str);
    }
    
    private static void splitDemo() {
//      String str = "1z.h  2an,gqqqqqq3. ||| 4  5wggggggg6o@@@@@7.fj.e####8h  9i,o.lk ^^^^1.,sj fa******.2k kkkkkk3 l.j.d ,f  4)))5sl.a$$$6kshfka$$$727593";
        String str = "1z.h  2an,g3. ||| 4  5wgg gg ggg6o@@@@@7.fj.";
        // 这里需要转移字符，且是双反斜线；
        // String regex = "\\.";
        // String regex = ".";//切出来的是个空的，是按照任意字符切的；
        // String regex = " +";//按照空格切的，可能有多个空格
//      String regex = "(.)\\1++";// 按照任意叠词（指定最少的个数）拆分；
        String regex = "(.)\\1{2}";// 按照任意叠词（指定个数为2+1）拆分；
        String regex1 = "(.)\\1{3}";// 按照任意叠词（指定个数为3+1）拆分；
//      String regex2 = "(.)\\1{3}";// 按照任意叠词（指定个数为3+1或者大于3+1）拆分；
        // 小括号()在正则中封装规则时使用；
        //"(.)\\1+"中，(.)代表任意字符，且为第一组数据，"\\1"代表第二个数据取 第一组数据 的值，{2}代表第二组数据的重复次数
        //想要复用，首先进行封装，然后七个名字，就可以重复调用了，这就是这里的封装的思想（对已经存在的规则进行封装复用），在正则表达式中封装使用小括号封装；
        //正则每一个小括号等状体称之为组，从1开始，组可以通过编号调用。组号和\\组合使用就可以了。必须先存在，然后才能被引用。
        //捕获组可以通过从左到右计算其开括号来编号
        //((A)(B(C))):1--((A)(B(C))); 2--\A; 3--(B(C)); 4--(C);

        String[] strs = str.split(regex);
        System.out.println("strs.length=" + strs.length + "!!!");
        for (String s : strs)
            System.out.print("<" + s + ">");
        System.out.println();
        
        String[] strs1 = str.split(regex1);
        System.out.println("strs1.length=" + strs1.length + "!!!");
        for (String s : strs1)
            System.out.print("<" + s + ">");
        System.out.println();

    }
    
    private static void checkTelphone() {
        String num = "18611478781";
        boolean b = num.matches("[1][3458][0-9]{9}");

        System.out.println("regexTest.checkTelphone():" + b);
    }
    
    @Test
    public void checkHTTPURL() throws Exception {
        String urlRegex="^http://[a-zA-Z_0-9/:\\-]*/simpleUrl/\\d{1}.htm$";
        String url="http://localhost:8080/personal-web-project/simpleUrl/0.htm";
         urlRegex="^\\S+/simpleUrl/\\d{1}\\.htm$";
         urlRegex="^\\S+/simpleUrl/\\d{1}\\.htm$";
         urlRegex="^.+/simpleUrl/[0-9].htm$";
         //urlRegex="^\\S+.+/simpleUrl/\\d{1}\\.htm$";
         //urlRegex="^.+/simpleUrl/\\d{1}\\.htm$";
         //urlRegex="^\\S*\\./simpleUrl/\\d{1}\\.htm$";//不可以
        System.out.println(url.matches(urlRegex));
    }
    
    /**
     * 过滤非数字及字母的字符串
     * 几个特殊的字符：“.”==="\\.",和"-"==="\\-",其他见下面
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        //[a-zA-Z_0-9]
        String regex="[^\\w]||[^0-9]";
        //regex="[a-z]|[0-9]|[A-Z]";
        String validateCode="abcdegKJSLf123P&*()a";
        validateCode= validateCode.replaceAll(regex, "");
        System.out.println("正则测试："+validateCode);
    }

	public static void main(String[] args) {
		// 两种正则匹配方式
		p("abc".matches("..."));
		p("a8729a".replaceAll("\\d", "-"));
		Pattern p = Pattern.compile("[a-z]{3}");
		Matcher m = p.matcher("fgh");
		p(m.matches());
		p("fgha".matches("[a-z]{3}"));
		
		// IP地址
		p("192.168.0.aaa".matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));
		p("192".matches("[0-2][0-9][0-9]"));
		
		// 反斜线匹配需要注意
		p("\\".matches("\\\\"));
		p(" \n\r\t".matches("\\s{4}"));
		p(" ".matches("\\S"));
		p("a_8".matches("\\w{3}"));
		p("abc888&^%".matches("[a-z]{1,3}\\d+[&^#%]+"));
		
		//POSIX Style
		p("a".matches("\\p{Lower}"));
		
		//boundary
		//whilte lines
		p(" \n".matches("^[\\s&&[^\\n]]*\\n$"));
		p("hello sir".matches("^h.*"));
		p("hello sir".matches(".*ir$"));
		p("hello sir".matches("^h[a-z]{1,3}o\\b.*"));
		p("hellosir".matches("^h[a-z]{1,3}o\\b.*"));
		p("aaa 8888c".matches(".*\\b\\d{4}."));
		p("aaa8888c".matches(".*\\b\\d{4}."));
		
		//email
		p("asdfasdfsafsf@dsdfsdf.com".matches("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+"));
		
		//matches find lookingAt
        p = Pattern.compile("\\d{3,5}");
        String s = "123-34345-234-00";
        m = p.matcher(s);
		p(m.matches());
		m.reset();
		p(m.find());
		p(m.start() + "-" + m.end());
		p(m.find());
		p(m.start() + "-" + m.end());
		p(m.find());
		p(m.start() + "-" + m.end());
		p(m.find());
		//当m不存在的时候，会报空指针
		if(m.find()){
		    p(m.start() + "-" + m.end());
		}
		//首次找到匹配对象
		p(m.lookingAt());
		p(m.lookingAt());
		p(m.lookingAt());
		p(m.lookingAt());
		
		//replacement
        p = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
        m = p.matcher("java Java JAVa JaVa IloveJAVA you hateJava afasdfasdf");
        StringBuffer buf = new StringBuffer();
        int i = 0;
        while (m.find()) {
            i++;
            if (i % 2 == 0) {
                m.appendReplacement(buf, "java");
            } else {
                m.appendReplacement(buf, "JAVA");
            }
        }
        m.appendTail(buf);
        p(buf);
		
		
		//group，可以查找符合制动规则的字符串的个个数，或者显示符合指定规则的数据
        p = Pattern.compile("(\\d{3,5})([a-z]{2})");
        s = "123aa-34345bb-234cc-00";
        m = p.matcher(s);
        while (m.find()) {
            p(m.group());
        }
		
		//qulifiers
        p = Pattern.compile(".{3,10}+[0-9]");
        s = "aaaa5bbbb68";
        m = p.matcher(s);
        if (m.find()) {
            p(m.start() + "-" + m.end());
        } else {
            p("not match!");
        }
		
		//non-capturing groups
        p = Pattern.compile(".{3}(?=a)");
        s = "444a66b";
        m = p.matcher(s);
        while (m.find()) {
            p(m.group());
            p(m.group(1));
        }
		
		//back refenrences
        p = Pattern.compile("(\\d(\\d))\\2");
        s = "122";
        m = p.matcher(s);
        p(m.matches());
		
        // flags，不区分大小写判断Pattern.CASE_INSENSITIVE
        p = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
        p("Java".matches("(?i)(java)"));
	}
	
	//打印方法
	public static void p(Object o) {
		System.out.println(o);
	}

}
