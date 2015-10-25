package base.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试页面Controller2
 */
@Controller
@RequestMapping("/base")
public class IndexController2 {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 测试页面1
     */
    @RequestMapping("/login")
    public String list(Model model) {
        System.out.println("Hello World!");
        logger.info("测试页面Controller执行了。。。");
        return "/base/login";
    }

    /**
     * 测试页面2
     */
    @RequestMapping("/login2")
    public String list2(Model model,HttpServletRequest request) {
        String queryString = request.getQueryString();
        
        System.out.println("Hello World2!"+queryString);
        logger.info("测试页面Controller方法2执行了。。。");
        return "/base/login";
    }
}
