package base.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

/**
 * URL权限检查拦截器，暂时没有获取session对象中的用户信息，作为测试类使用
 * 
 * 当mappingURL正则表达式匹配成功时，进行拦截，跳转到指定的页面；
 * 
 * @author Liubao
 * @2014年11月26日
 * 
 */
@SuppressWarnings("deprecation")
public class CommonInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public CommonInterceptor() {
    }

    // 利用正则映射到需要拦截的路径或者不需要拦截单个的URL，可以使用正则表达式通配
    private String urlRegex;

    public void setUrlRegex(String urlRegex) {
        this.urlRegex = urlRegex;
    }

    /**
     * 执行顺序解析：
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     * 
     */
    @SuppressWarnings({ "unused" })
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        
        
        //测试代码==============================
        
        //request对象的2种获取方式
        HttpServletRequest request1 = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletRequest request2 = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        //HttpServletRequest request2 = ServletActionContext.getRequest();
        
        //获取ServletContext对象的2种方式
        ServletContext servletContext1 = request.getSession().getServletContext();
        //ServletContext servletContext2 = ServletActionContext.getServletContext();
        
        //获取spring上下文的4种方式！ 
        WebApplicationContext applicationContext1 = RequestContextUtils.getWebApplicationContext(request);
        WebApplicationContext applicationContext2 = WebApplicationContextUtils.getWebApplicationContext(servletContext1);
        WebApplicationContext applicationContext3 = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext1);
        //通过ServletContext对象获取
        WebApplicationContext applicationContext4 = (WebApplicationContext) servletContext1.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        
        //response对象的获取方式,struts2的获取方式
        //HttpServletResponse response1 = ServletActionContext.getResponse();
        
        //Session对象的2种获取方式
        /**
         * true: 如果session存在，则返回该session，否则创建一个新的session；
         * false: 如果session存在，则返回该session，否则返回null.
         */
        //方式1
        HttpSession session1 = request.getSession(true);
        //方式2
        HttpSession session2 = request.getSession();
        
        //session的过期方法
        //session2.invalidate();
        //session的一些获取方法
        //获取request对应的session对象的id
        String sessionId2 = WebUtils.getSessionId(request2);
        String sessionId = session2.getId();
        /**
         * 获取session独享的key和values，可以进行强转或者String.valuesOf()进行取值；
         */
        String[] valueNames = session2.getValueNames();
            for (String valueName : valueNames) {
                Object attribute = session2.getAttribute(valueName);
            }
        //其他对象及属性
        ServletContext servletContext = session2.getServletContext();
        HttpSessionContext sessionContext = session2.getSessionContext();
        long creationTime = session2.getCreationTime();
        long lastAccessedTime = session2.getLastAccessedTime();
        Object sessionValue = session2.getValue("sessionName");
        /**
         * 获取request2对象中对应的key对应的属性值，例如项目的根路径如下
         */
        WebUtils.getSessionAttribute(request2, WebUtils.DEFAULT_WEB_APP_ROOT_KEY);
        String context_key = (String) WebUtils.getSessionAttribute(request,"context_key");
        
        /**
         * Cookie对象的2种获取方式；
         * 单位为秒
         * 正值表示cookie将在这么多秒以后失效。注意这个值是cookie将要存在的最大时间，而不是cookie现在的存在时间。
         * 负值表示当浏览器关闭时，Cookie将会被删除。
         * 0值则是要立刻删除该Cookie。
         * 或者设置一个Long的最大值Long.MAX_VALUE即可
         * 
         */
        //方式1
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            //get方法
            cookie.getName();
            cookie.getValue();
            cookie.getPath();
            cookie.getMaxAge();
            //set方法
            //0代表立刻过期，-1代表关闭浏览器时删除Cookie；单位为秒
            cookie.setMaxAge(0);
            //设置为根目录
            cookie.setPath("/");
            cookie.setValue("");
        }
        //方式2
        //名称可以自己指定，自己知道就行了
        Cookie cookie =new Cookie("cookName", "cookValue");
        //方式3，需要Cookie的名称获取
        WebUtils.getCookie(request2, "cookName");
        
        /**
         * 将Cookie的添加到request对象中，进行传递保存
         */
        response.addCookie(cookie);
        
        
        //测试代码==============================
        
        
        
        
        logger.info("==============执行顺序: 1、preHandle================");
        //TODO 获取访问路径
        //http://localhost:8080/personal-web-project/simpleUrl/0.htm
        String url = request.getRequestURL().toString();
        //正则表达式匹配需要拦截的URL信息,配置的URL不匹配或者为空时，则放行；
        //urlRegex="^\\S+/simpleUrl/\\d{1}\\.htm$";
        if (urlRegex != null && url.matches(urlRegex)) {
            
            //匹配成功，TODO 执行预定的指定任务，类似下面的请求转发或者重定向。
            
            // 方式1：重定向到指定登录页面,该页面不能存在于WEN-INF下面吗？
            //response.sendRedirect(request.getContextPath() + "/base/login");
            // 方式2：请求转发到指定登录页面
            request.getRequestDispatcher("/WEB-INF/jsp/base/login.jsp").forward(request,response); 
            return false;
        }
        //放行，继续执行
        return true;
    }

    /**
     *  在业务处理器处理请求执行完成后,生成视图之前执行的动作
     */
    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
        logger.info("==============执行顺序: 2、postHandle================");
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用
     * 
     * 当有拦截器抛出异常时,会从当前拦截器往回执行,所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("==============执行顺序: 3、afterCompletion================");
    }
    

}
