package base.controller.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import base.controller.test.ParameterException;


/**
 * 自定义的全局异常处理器
 * 
 * @author Liubao
 * @2014年11月28日
 * 
 */
public class CustomerExceptionHandler implements HandlerExceptionResolver
            /* extends SimpleMappingExceptionResolver */ {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("exception", exception);
        
        // 当ajax异常时，在ajax的error中可直接获得异常。普通的异常我们都配置好了界面，系统会自动跳转。
        String viewName=getViewNameByExceptionType(exception, model);
        
        if (viewName != null&&viewName!="") {
            // 判断不是ajax请求，直接为jsp格式返回View视图
            if (!(request.getHeader("accept").indexOf("application/json") > -1 
                    || (request.getHeader("X-Requested-With") != null && 
                    request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
                
                // 如果不是异步请求
                Integer statusCode = 0;
                //determineStatusCode(request, viewName);
                if (statusCode != null) {
                    //applyStatusCodeIfPossible(request, response, statusCode);
                }
                
                logger.info("返回jspn视图。。。"+viewName);
                return new ModelAndView(viewName, model);
            } else {
                // JSON格式视图返回
                try {
                    PrintWriter writer = response.getWriter();
                    
                    //TODO 这里可以返回我们需要的json格式数据
                    
                    writer.write(exception.getMessage());
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("返回json视图错误。。。");
                }
                return null;
            }
        } else {
            //返回不能识别的异常信息 TODO 
            logger.warn("返回不能识别的异常视图信息 。。。");
            return null;
            
        }

    }

    /**
     * 根据不同错误转向不同页面，一般这里写的都是自己知道的可以预测的自定义异常信息
     */
    private String getViewNameByExceptionType(Exception exception,Map<String, Object> model) {
        
        if (exception instanceof IOException) {
            return "error-1";
        } else if (exception instanceof ParameterException) {
            return "error-2";
        } else if (exception instanceof SQLException) {
            return "error-sql";
        } else {
            return "error";
        }
    }
    
    
}
