package validatecode;



import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 验证码校验服务Controller
 * 
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("/bygj")
public class BygjValidateController {
   
    /** 日至服务. */
    private static final Logger logger = 
            LoggerFactory.getLogger(BygjValidateController.class);
    
    /**
     * 功能描述: 获取验证码<br>
     * 
     * @param request the request
     * @param response the response
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/validateCode")
    public void validateCode(HttpServletRequest request,
            HttpServletResponse response) {
        System.setProperty("java.awt.headless", "true");
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode validateCode = new RandomValidateCode();
        BufferedImage image = validateCode.getValidateImage();
        HttpSession session = request.getSession();
        session.setAttribute(RandomValidateCode.RANDOMCODEKEY,
                validateCode.getCodeString());
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
            System.setProperty("java.awt.headless", "true");
        } catch (IOException e) {
            logger.error("validateCode image transmission was error !", e);
        }
    }
    

}
