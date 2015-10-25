package base.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Liubao
 * @2014年11月27日
 * 
 */
@Controller
@RequestMapping("/simpleUrl")
public class SimpleUrlController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 测试页面1
     */
    @RequestMapping("/0")
    public String list(Model model) {
        logger.info("测试页面SimpleUrlController执行了。。。");
        //默认是转发操作
        return "/base/simpleUrl";
    }
    
    @RequestMapping("/1")
    public String list2(Model model ,HttpServletRequest request) throws NullPointerException {
        logger.info("测试页面SimpleUrlController执行了。。。");
        //默认是转发操作
        if(request.getParameter("flag")==null){
            logger.error("测试页面SessionTimeoutException。。。");
            throw new NullPointerException("");
        }
        return "/base/login";
    }
    
}
