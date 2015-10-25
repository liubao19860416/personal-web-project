package base.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
/**
 * 多种格式的邮件发送程序 --OK
 * 
 * @author Liubao
 * @2014年11月30日
 *
 */
public class SpringMailSender {

    // Spring的邮件工具类，实现了MailSender和JavaMailSender接口
    private JavaMailSenderImpl mailSender;
    private static String host = "smtp.qq.com";
    private static String username = "573393741@qq.com";
    private static String password = "liubao19900306";
    private static String mailTo = "18611478781@163.com";

    public SpringMailSender() {
        // 初始化JavaMailSenderImpl，当然推荐在spring配置文件中配置，这里是为了简单
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
    }

    /**
     * 简单邮件发送
     */
    public void sendSimpleMail() {
        // 构建简单邮件对象，见名知意
        SimpleMailMessage smm = new SimpleMailMessage();
        // 设定邮件参数
        smm.setFrom(mailSender.getUsername());
        smm.setTo(mailTo);
        smm.setSubject("Hello world");
        smm.setText("Hello world via spring mail sender");
        // 发送邮件
        mailSender.send(smm);
    }

    /**
     * 带附件的邮件发送
     */
    public void sendAttachedFileMail() throws MessagingException {
        // 使用JavaMail的MimeMessage，支付更加复杂的邮件格式和内容
        MimeMessage msg = mailSender.createMimeMessage();
        // 创建MimeMessageHelper对象，处理MimeMessage的辅助类
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        // 使用辅助类MimeMessage设定参数
        helper.setFrom(mailSender.getUsername());
        helper.setTo(mailTo);
        helper.setSubject("Hello Attachment");
        helper.setText("This is a mail with attachment");
        // 加载文件资源，作为附件
        ClassPathResource file = new ClassPathResource("base/mail/image.jpg");
        // 加入附件
        helper.addAttachment("image.jpg", file);
        // 发送邮件
        mailSender.send(msg);
    }

    /**
     * 发送富文本邮件
     */
    public void sendRichContentMail() throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom(mailSender.getUsername());
        helper.setTo(mailTo);
        helper.setSubject("Rich content mail");
        // 第二个参数true，表示text的内容为html，然后注意<img/>标签，src='cid:file'，'cid'是contentId的缩写，'file'是一个标记，
        // 需要在后面的代码中调用MimeMessageHelper的addInline方法替代成文件
        helper.setText("<body><p>Hello Html Email</p><img src='cid:file'/></body>",true);
        FileSystemResource file = new FileSystemResource("D:\\eclipse-20141015\\workspace\\personal-web-project\\src\\main\\java\\base\\mail\\image.jpg");
        helper.addInline("file", file);
        mailSender.send(msg);
    }
    
    public static void main(String[] args) {
        //发送简单邮件
        //new SpringMailSender().sendSimpleMail();
        try {
            //带附件的邮件发送
            //new SpringMailSender().sendAttachedFileMail();
            //发送富文本邮件
            new SpringMailSender().sendRichContentMail();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("邮件发送结束了。。。");
    }

}
