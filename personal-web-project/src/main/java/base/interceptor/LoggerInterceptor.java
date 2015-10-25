package base.interceptor;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.meidusa.fastjson.JSON;
/**
 * 记录日志信息的拦截器
 * 
 * @author Liubao
 * @2014年11月30日
 *
 */
public class LoggerInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 使用了线程安全的ThreadLocal线程局部变量，记录当前日志对象信息
     */
    private static NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("");

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        // 1、开始时间
        long startTime = System.currentTimeMillis();
        
        // 线程绑定变量（该数据只有当前请求的线程可见）
        startTimeThreadLocal.set(startTime);

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object bean = handlerMethod.getBean();
        Method method = handlerMethod.getMethod();

        ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method,ResponseBody.class);

        logger.info("当前时间[" + new Date().toLocaleString() + "]登录用户访问URL信息为："+ JSON.toJSONString(request.getContextPath()));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long startTime = startTimeThreadLocal.get();
        long consumeTime = endTime - startTime;
        logger.warn("当前访问网址用时>=标准时间[,"
                + String.format("%s consume %d millis", request.getRequestURL()
                        .toString(), consumeTime));
    }
    
    
    

}
