package regex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮箱爬虫工具
 * @author Liubao
 * @2014年11月16日
 *
 */
public class EmailSpider {

	@SuppressWarnings("resource")
    public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("D:\\share\\courseware\\1043633.html"));
			String line = "";
			while((line=br.readLine()) != null) {
				parse(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void parse(String line) {
		Pattern p = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
		Matcher m = p.matcher(line);
		while(m.find()) {
			System.out.println(m.group());
		}
	}

}
