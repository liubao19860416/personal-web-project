package base.mail.mail02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import org.apache.commons.mail.SimpleEmail;

/**
 * @author Administrator
 * 演示Apache公司的JavaMail发送邮件到sina的邮件服务器
 */
public class Demo02 {
	public static void main(String[] args) throws Exception{
		
		//创建一个简单邮件
		SimpleEmail simpleEmail = new SimpleEmail();
		
		//设置邮件内容的编码方式 
		simpleEmail.setCharset("UTF-8");
		
		
		//设置邮件服务器域名或IP地址，例如：smtp.163.com
		simpleEmail.setHostName("smtp.163.com");
		
		//发送细节信息显示出来
		simpleEmail.setDebug(true);
		
		//参数一：你在163上注册的帐号
		//参数二：你在163上注册的帐号对应密码
		simpleEmail.setAuthentication("runsin0723",get163password());

		//设置邮件的相关属性内容
		simpleEmail.setFrom("runsin0723@163.com");
		simpleEmail.addTo("zhaojun07232014@sina.com");
		simpleEmail.setSubject("测试");
		simpleEmail.setSentDate(new Date());
		simpleEmail.setMsg("用Apache的Email组件开发邮件程序");
		
		//发送邮件
		simpleEmail.send();
		
	}
	private static String get163password() throws Exception{
		File file = new File("e:/163password.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		return bufferedReader.readLine();
	}
}





