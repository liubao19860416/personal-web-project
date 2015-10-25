package base.controller.handler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import base.controller.test.ParameterException;

/**
 * 方式1：所有的异常信息，在Controller层，都不用捕获，全部抛出，由配置的Spring的全局异常处理器统一处理；
 * 方式2：所有的异常信息，在Controller层同意进行捕获，然后返回对应的异常对应的url映射地址；（不方便使用）
 * 
 * @author Liubao
 * @2014年11月28日
 * 
 */
@Controller
public class AnnotationExceptionHandlerController {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 基于@ExceptionHandler异常处理
     * 可以一个处理自定义异常，一个处理其他未知系统异常
     */
    @ExceptionHandler(value = { IOException.class, SQLException.class ,ParameterException.class})
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception exception) {
        
        //默认应该是使用的转发方式返回的视图？
        request.setAttribute("exception", exception);
        
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("exception", exception);

        // 根据不同错误转向不同页面
        if (exception instanceof IOException) {
            return new ModelAndView("error-1", model);
        } else if (exception instanceof ParameterException) {
            return new ModelAndView("error-2", model);
        } else if (exception instanceof SQLException) {
            return new ModelAndView("error-sql", model);
        } else {
            return new ModelAndView("error");
        }
    }
    
    @ExceptionHandler({ Exception.class })
    public String exceptionHandler(Exception e) {
        System.out.println(e.getMessage());
        return "exception";
    }
    
}
