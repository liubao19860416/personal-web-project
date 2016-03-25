package base.interceptor;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import base.annotation.Auth;
import base.entiy.User;

import com.alibaba.fastjson.JSON;
/**
 * 通过请求的方法的注解信息，进行拦截器；
 * 当包含注解信息@Auth的时候，进行拦截
 * 
 * @author Liubao
 * @2014年11月26日
 *
 */
public class AnnotationAuthInterceptor extends HandlerInterceptorAdapter {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 方法执行前，调用执行的拦截器方法，用来做权限验证等操作
     */
    @SuppressWarnings("deprecation")
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
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
        //测试查看，获取当前拦截对象的相关信息
        HandlerMethod handlerMethod=(HandlerMethod) handler;
        @SuppressWarnings("unused")
        Object bean = handlerMethod.getBean();
        Method method = handlerMethod.getMethod();
        
        /**
         * TODO 获取定义的指定的注解信息，进行相应的控制，如权限控制拦截
         * 找action方法是否是标注了responseBody注解，类似操作自定义注解信息@Auth，Authorized，授权的
         */
        //判断是否是我们需要的指定的注解类型
        if (method.isAnnotationPresent(Auth.class)) {
            // Annotation authAnnotation = method.getAnnotation(Auth.class);
            // Auth authAnnotation = handlerMethod.getMethodAnnotation(Auth.class);
            Auth authAnnotation = AnnotationUtils.findAnnotation(method,Auth.class);

//            authAnnotation.key();
            String value2 = authAnnotation.value();
            
            
            if (authAnnotation != null) {
                // TODO 获取注解的一些相关信息
                Map<String, Object> attributes = AnnotationUtils.getAnnotationAttributes(authAnnotation);
                for (String key : attributes.keySet()) {
                    String value= (String)attributes.get(key);
                    System.out.println(key+"====获取注解属性方式1=====>>>"+value);
                }
                
                //获取当前注解方法的返回值，可能是多个？？？
                Method[] methods = authAnnotation.getClass().getDeclaredMethods();
                for (Method method2 : methods) {
                    String[] values = (String[]) method2.invoke(authAnnotation, null);
                    for (String key : values) {
                        System.out.println(method.getName()+"====方法的返回值为=====>>>" + key);
                    }
                }
            }

        }
        
        String requestUrl2 = request.getRequestURL().toString();
        //记录当前访问路径信息
        logger.info("当前时间["+new Date().toLocaleString()+"]登录用户访问URL信息为："+JSON.toJSONString(requestUrl2));
        
        // 继续流程执行
        return true;
    }

    /**
     * 3.方法执行后，调用执行的拦截器方法,用来做全局异常信息处理
     */
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception exception) throws Exception {
        super.afterCompletion(request, response, handler, exception);
    }

    /**
     * 2.位于上面的两者之间执行的方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
    
    
}
