package base.mail.mail02;

import java.util.Date;
import org.apache.commons.mail.SimpleEmail;

/**
 * @author Administrator
 * 演示Apache公司的JavaMail发送邮件
 */
public class Demo03 {
	public static void main(String[] args) throws Exception{
		
		//创建一个简单邮件
		SimpleEmail simpleEmail = new SimpleEmail();
		
		//设置邮件内容的编码方式 
		simpleEmail.setCharset("UTF-8");
		
		//设置邮件服务器域名或IP地址
		simpleEmail.setHostName("127.0.0.1");
		
		//设置邮件的相关属性内容
		simpleEmail.setFrom("aaa@zhaojun.com");
		simpleEmail.addTo("bbb@qq.cn");
		simpleEmail.setSubject("大家好");
		simpleEmail.setSentDate(new Date());
		simpleEmail.setMsg("来到上海我很开心");
		
		//发送邮件
		simpleEmail.send();
		
	}
}
