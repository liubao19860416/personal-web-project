package base.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * velocity模版的邮件发送程序
 * 
 * @author Liubao
 * @2014年11月30日
 * 
 */
public class VelocityTemplateMailMailSender {
    
    // Spring的邮件工具类，实现了MailSender和JavaMailSender接口
    private JavaMailSenderImpl mailSender;
    private VelocityEngine velocityEngine;
    
    private static String host = "smtp.qq.com";
    private static String username = "573393741@qq.com";
    private static String password = "liubao19900306";
    private static String mailTo = "18611478781@163.com";

    public VelocityTemplateMailMailSender() {
        // 初始化JavaMailSenderImpl，当然推荐在spring配置文件中配置，这里是为了简单
        mailSender = new JavaMailSenderImpl();
        // 设置参数
        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        // Velocity的参数，通过VelocityEngineFactoryBean创建VelocityEngine，也是推荐在配置文件中配置的
        Properties props = System.getProperties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        VelocityEngineFactoryBean v = new VelocityEngineFactoryBean();
        v.setVelocityProperties(props);
        try {
            velocityEngine = v.createVelocityEngine();
        } catch (VelocityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用Velocity模板发送邮件
     */
    @SuppressWarnings("deprecation")
    public void sendVelocityTemplateMail() throws MessagingException {
        // 声明Map对象，并填入用来填充模板文件的键值对
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", "liubao");
        model.put("content", "Hello，spring mail");
        // Spring提供的VelocityEngineUtils将模板进行数据填充，并转换成普通的String对象
        String emailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "base/mail/index.vm", model);
        // emailText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"index.vm", "UTF-8", model);
        // 和上面一样的发送邮件的工作
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom(mailSender.getUsername());
        helper.setTo(mailTo);
        helper.setSubject("Velocity模板发送邮件");
        helper.setText(emailText, true);
        mailSender.send(msg);
    }
    
    public static void main(String[] args) {
        try {
            new VelocityTemplateMailMailSender().sendVelocityTemplateMail();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("邮件发送结束了。。。");
    }

}
