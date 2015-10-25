package base.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import time.RemoteTimeUtil;
import base.ApplicationContextHelper;


/**
 * 测试页面Controller，测试ajax请求json数据
 */
@Controller
@RequestMapping("/")
public class IndexController {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    //他是在哪里注入的，使用时需要注意
    @Autowired
    private ApplicationContextHelper helper;
    
    @RequestMapping(value = "/data")
    public ModelAndView toJsonIndex(Model model,HttpServletRequest request) {
        WebApplicationContext applicationContext1 = RequestContextUtils.getWebApplicationContext(request);
        MessageSource  messageSource=(MessageSource) applicationContext1.getBean(ResourceBundleMessageSource.class);
        messageSource=(MessageSource) applicationContext1.getBean("resourceBundleMessageSource");
        Object[] args={"john",new GregorianCalendar().getTime()};
        String message = messageSource.getMessage("username.is.null", args, Locale.CHINA);
        message = messageSource.getMessage("mobile.is.null", args, Locale.US);
        
        //方式2 报错
        // messageSource=(MessageSource) helper.getBean("resourceBundleMessageSource");
        //message = messageSource.getMessage("username.is.null", args, Locale.CHINA);
        System.out.println(message);
        //从spring的ResourceBundleMessageSource读取的配置文件中获取属性信息，其中value为配置文件中的key值，
        //key是在页面访问使用${key}，这时候，在页面中获取的值就是配置文件中的对应的value值了。
        //model.addAttribute("message",message.trim());
        model.addAttribute("message","mobile.is.null");
        return new ModelAndView("/base/data");
    }
    
    @RequestMapping(value = "/queryForJsonDataList")
    public @ResponseBody List<String> queryForJsonDataList(HttpServletRequest request) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("数值" + i);
        }
        String parameter = request.getParameter("tag");
        list.add("获取的参数："+parameter);
        return list;
    }
    
    @RequestMapping("/resourceBundle")
    public String list3(Model model) {
        System.out.println("Hello World!");
        logger.info("测试页面resourceBundle执行了。。。" );
        return "resourceBundle";
    }
    
    /**
     * 测试页面1
     */
    @RequestMapping("/index1")
    public String list(Model model) {
        System.out.println("IndexController---Hello World!");
        logger.info("测试页面Controller执行了list方法。。。" );
        return "index1";
    }
    
    /**
     * 测试页面2
     */
    @RequestMapping("/index2")
    public String list2(Model model) {
        System.out.println("IndexController---Hello World2!");
        logger.info("测试页面Controller方法list2执行了。。。" );
        return "index2";
    }
    
    private static RemoteTimeUtil remoteTimeUtil = new RemoteTimeUtil();
    /**
     * 测试页面:显示当前时间，自动进行刷新显示
     */
    @SuppressWarnings({ "static-access" })
    @RequestMapping("/time")
    public String showTimeNow(Model model) throws IOException {
        logger.info("测试页面Controller方法showTimeNow执行了。。。" );
        long time1=System.currentTimeMillis();
        long time2 =  remoteTimeUtil.getNetTime("http://www.rjh.com.cn/2013ruijin/ruijin/sy/index.shtml");
        long time3 =  remoteTimeUtil.getNetTime("http://yuyue.rjh.com.cn/Info/Terms");
//        long time4 =  remoteTimeUtil.getNetTime("https://www.baidu.com/");
//        long time5 =  remoteTimeUtil.getNetTime("http://open.baidu.com/special/time/");
//        long time6 =  remoteTimeUtil.getNetTime("http://www.114time.com/");
        System.out.println(time1);
        Timestamp time000 = new Timestamp(time2);
        time000.setSeconds(time000.getSeconds()+1);
        time2=time000.getTime();
        System.out.println(time2);
        System.out.println(time3);
//        System.out.println(time4);
//        System.out.println(time5);
//        System.out.println(time6);
        System.out.println("============================");
        model.addAttribute("time1", time1);
        model.addAttribute("time2", time2);
        model.addAttribute("time3", time3);
        model.addAttribute("time4", 0);
        model.addAttribute("time5", 0);
        model.addAttribute("time6", 0);
        return "time";
    }
}
