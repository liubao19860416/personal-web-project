package base.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * spring的简单邮件发送实现 OK
 * 
 * @author Liubao
 * @2014年11月30日
 * 
 */
public class SpringSimpleEmailSender {
    
    @SuppressWarnings("resource")
    public static  void sendSimpleEmail(){
        try {
            //ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-mail.xml");
            ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:base/mail/applicationContext-mail.xml");
            JavaMailSender sender = (JavaMailSender) ctx.getBean("javaMailSender");
            MimeMessage msg = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
            helper.setFrom("573393741@qq.com");
            helper.setTo("18611478781@163.com");
            helper.setText("tets this is a spring mvc email");
            helper.setSubject("邮件主题：测试邮件！");
            sender.send(msg);
            System.out.println("email send ok");
        } catch (MessagingException e) {
            System.out.println("send fail");
            e.printStackTrace();
        }

    }
    
    public static void main(String[] args) {
        SpringSimpleEmailSender.sendSimpleEmail();
    }
}
