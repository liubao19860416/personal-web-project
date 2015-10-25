package base.mail.mail02;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Administrator
 * 演示SUN公司的JavaMail发送邮件
 */
public class Demo01 {
	public static void main(String[] args) throws Exception{
		
		//封装客户端与易邮邮件服务器的通信协议和主机域名或IP地址
		Properties props = new Properties();
		props.setProperty("mail.host","127.0.0.1");
		props.setProperty("mail.transport.protocol","smtp");
		
		//获取Session
		Session session = Session.getDefaultInstance(props);
		
		//创建一封邮件
		Message message = new MimeMessage(session);
		
		//设置邮件的相关信息
		message.setFrom(new InternetAddress("aaa@zhaojun.com"));
	
		message.setRecipient(RecipientType.TO,new InternetAddress("bbb@qq.cn"));
		
		message.setSubject("测试");
		message.setText("你好,现在我们学习Sun公司的JavaMai编程");
		
		//获取Transport对象
		Transport transport = session.getTransport();
		
		//发邮件人(自已)，与易邮邮件服务器进行连接，需要帐号和密码，注意：这里的帐号和密码必须是在易邮邮件服务器上注册合法的
		transport.connect("aaa@zhaojun.com","123456");
		
		//发送邮件
		transport.send(message);

		//关闭资源
		transport.close();
	}
}
