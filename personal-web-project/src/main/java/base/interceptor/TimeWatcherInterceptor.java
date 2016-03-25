package base.interceptor;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import base.entiy.User;

import com.alibaba.fastjson.JSON;
/**
 * 性能监控（执行时间）拦截器
 * 
 * 关键一点interceptor不是线程安全的。我们需要借助threadlocal来实现线程安全
 * 
 * @author Liubao
 * @2014年11月26日
 *
 */
public class TimeWatcherInterceptor extends HandlerInterceptorAdapter {
    
    /**
     * 默认临界阈值为500毫秒
     */
    private static final long DEFAULT_RECORDTIMEPOINT = 500l;
    
    private long recordTimePoint;
    
    //set方法进行注入属性就行了
    public void setRecordTimePoint(long recordTimePoint) {
        this.recordTimePoint = recordTimePoint;
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 使用了线程安全的ThreadLocal线程局部变量(一个继承了ThreadLocal的子类NamedThreadLocal)
     * 
     * 根据名字，唯一表示该NamedThreadLocal线程对象
     */
    private static NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

    /**
     * 方法执行前，调用执行的拦截器方法
     */
    @SuppressWarnings("deprecation")
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        // 1、开始时间
        long startTime = System.currentTimeMillis();
        // 线程绑定变量（该数据只有当前请求的线程可见）
        startTimeThreadLocal.set(startTime);
        /**
         * 在这里记录当前登录的人员的信息，并将其信息保存到指定的级别的log日志文件中
         */
        // 获取系统session
        HttpSession session = request.getSession();
        // 获取用户信息
        Object attribute = session.getAttribute("activeUser");
        User activeUser = null;
        //进行类型判断
        if(attribute!=null && attribute instanceof User ){
            activeUser = (User) session.getAttribute("activeUser");
        }

        if (activeUser != null) {
            // 记录当前登录用户相关信息，使用默认info级别
            logger.info("当前时间["+new Date().toLocaleString()+"]登录用户信息为："+JSON.toJSONString(activeUser));
        }
        /**
         * TODO 测试查看，获取当前拦截对象的相关信息;但不是默认的DefaultServletHttpRequestHandler处理器的时候，进行拦截
         */
        //public java.lang.String base.controller.SimpleUrlController.list2(org.springframework.ui.Model,javax.servlet.http.HttpServletRequest) throws java.lang.NullPointerException
        HandlerMethod handlerMethod=(HandlerMethod) handler;
        //base.controller.SimpleUrlController@3bef5460
        Object bean = handlerMethod.getBean();
        //public java.lang.String base.controller.SimpleUrlController.list2(org.springframework.ui.Model,javax.servlet.http.HttpServletRequest) throws java.lang.NullPointerException
        Method method = handlerMethod.getMethod();
        
        /**
         * TODO 获取定义的指定的注解信息，进行相应的控制，如权限控制拦截
         * 找action方法是否是标注了responseBody注解，类似操作自定义注解信息@Auth，Authorized，授权的
         */
        ResponseBody methodAnnotation = handlerMethod.getMethodAnnotation(ResponseBody.class);
        ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method,ResponseBody.class);
        
        if(responseBodyAnn!=null){
            //说明方法返回的ResponseBody注解的,处理返回类型为json异常信息
            //return this.handlerResolveException(exceptionResultInfo,request, response);
        }
        
        // 获取访问路径,/personal-web-project/simpleUrl/0.htm
        //String requestURI = request.getRequestURI();
        request.getContextPath();
        String requestUrl2 = request.getRequestURL().toString();
        //记录当前访问路径信息
        logger.info("当前时间["+new Date().toLocaleString()+"]登录用户访问URL信息为："+JSON.toJSONString(requestUrl2));
        
        //requestUrl=requestURI.replace(request.getContextPath(), "");///simpleUrl/0.htm
        //requestUrl=requestURI.substring(0, requestURI.indexOf("."));
        
        //在这里使用contains判断的方式不合适
        /*if (unCheckURLs.contains(requestURI)) {
            return true;
        }*/ 
        // 继续流程执行
        return true;
    }

    /**
     * 方法执行后，调用执行的拦截器方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 2、结束时间
        long endTime = System.currentTimeMillis();
        // 得到线程绑定的局部变量（开始时间）
        long startTime = startTimeThreadLocal.get();
        // 3、消耗的时间
        long consumeTime = endTime - startTime;
        if(recordTimePoint==0){
            recordTimePoint=DEFAULT_RECORDTIMEPOINT;
        }
        if (consumeTime >= recordTimePoint) {
            // 此处认为处理时间超过recordTimePoint毫秒的请求为慢请求
            //  记录到日志文件,warn日志级别
            logger.warn("当前访问网址用时>=标准时间["+recordTimePoint+"]毫秒,"+String.format("%s consume %d millis",request.getRequestURL().toString(), consumeTime));
            //System.out.println(String.format("%s consume %d millis",request.getRequestURI(), consumeTime));
        }
    }

    /**
     * 位于上面的两者之间执行的方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //logger.info("==============执行顺序: 2、postHandle================");
        super.postHandle(request, response, handler, modelAndView);
    }
    
    
}
