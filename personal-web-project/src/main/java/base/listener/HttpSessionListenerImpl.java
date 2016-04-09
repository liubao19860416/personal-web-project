package base.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

/**
 * 利用HttpSessionListener统计最多在线用户人数
 * 
 * @author Liubao
 * @2014年11月25日
 * 
 */
public class HttpSessionListenerImpl implements HttpSessionListener {
    
    private static Logger logger = LoggerFactory.getLogger(HttpSessionListenerImpl.class);

    /**
     * 监听Session创建的时候
     */
    @SuppressWarnings({ "unused" })
    public void sessionCreated(HttpSessionEvent event) {
        //获取用户会话的sessioonId和创建时间信息
        HttpSession session = event.getSession();
        String id = session.getId();
        long creationTime = session.getCreationTime();
        
        //测试代码开始=============需要实现ServletContextListener的监听器方式有稍微差别========================
       
        //request对象的2种获取方式
        HttpServletRequest request1 = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletRequest request2 = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        //HttpServletRequest request2 = ServletActionContext.getRequest();
        /**
         * 执行方法的时候，这里会导致循环调用 ，死循环
         */
        //获取ServletContext对象的3种方式
        ServletContext servletContext1 = session.getServletContext();
        //ServletContext servletContext2 = request1.getSession().getServletContext();
        //ServletContext servletContext3 = ServletActionContext.getServletContext();
        
        
        //获取spring上下文的4种方式！ 
        WebApplicationContext applicationContext1 = RequestContextUtils.getWebApplicationContext(request1);
        WebApplicationContext applicationContext2 = WebApplicationContextUtils.getWebApplicationContext(servletContext1);
        WebApplicationContext applicationContext3 = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext1);
        //通过ServletContext对象获取
        WebApplicationContext applicationContext4 = (WebApplicationContext) servletContext1.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        
        //response对象的获取方式,struts2的获取方式
        //HttpServletResponse response1 = ServletActionContext.getResponse();
        //servletContext1.getContextPath();

        //Session对象的2种获取方式
        /**
         * true: 如果session存在，则返回该session，否则创建一个新的session；
         * false: 如果session存在，则返回该session，否则返回null.
         */
        //方式1
       // HttpSession session1 = request1.getSession(true);
        //方式2
        //HttpSession session2 = request1.getSession();
        
        //session的过期方法
        //session2.invalidate();
        //session的一些获取方法
        //获取request对应的session对象的id
        String sessionId2 = WebUtils.getSessionId(request2);
        //String sessionId = session2.getId();
        /**
         * 获取session独享的key和values，可以进行强转或者String.valuesOf()进行取值；
         */
        //String[] valueNames = session2.getValueNames();
        //for (String valueName : valueNames) {
        //    Object attribute = session2.getAttribute(valueName);
        //}
        //其他对象及属性
        //ServletContext servletContext = session2.getServletContext();
        //HttpSessionContext sessionContext = session2.getSessionContext();
        //long creationTime = session2.getCreationTime();
        //long lastAccessedTime = session2.getLastAccessedTime();
        //Object sessionValue = session2.getValue("sessionName");
        /**
         * 获取request2对象中对应的key对应的属性值，例如项目的根路径如下
         */
        WebUtils.getSessionAttribute(request2, WebUtils.DEFAULT_WEB_APP_ROOT_KEY);
        String context_key = (String) WebUtils.getSessionAttribute(request1,"context_key");
        
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
        Cookie[] cookies = request1.getCookies();
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
        //response2.addCookie(cookie);
        
        //测试代码结束=====================================
        
        
        ServletContext context = session.getServletContext();
        //获取session中的在线人数属性值
        int maxOnLineCount = 10;
        int count = 0;
        if(context.getAttribute("maxOnLineCount")!=null){
            maxOnLineCount = Integer.parseInt(String.valueOf(context.getAttribute("maxOnLineCount")));
        }
        if(context.getAttribute("onLineCount")!=null){
            count = Integer.parseInt(String.valueOf(context.getAttribute("onLineCount")));
        }
        context.setAttribute("onLineCount",  ++count);
        logger.info("监听Session创建的了，当前在线人数为："+count );
        if (count >= maxOnLineCount) {
            // 记录最多人数是多少
            context.setAttribute("maxOnLineCount", count);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 记录在那个时刻达到上限
            context.setAttribute("date", df.format(new Date()));
            logger.info("当前在线人数已经达到最大值maxOnLineCount为："+count+",当前时间为：" +df.format(new Date()));
        }
    }

    
    /**
     *  监听session注销、超时时候调用，停止tomcat不会调用该方法
     */
    public void sessionDestroyed(HttpSessionEvent event) {
        //获取用户会话的sessioonId和创建时间信息
        HttpSession session = event.getSession();
        String id = session.getId();
        long creationTime = session.getCreationTime();
        
        ServletContext context = session.getServletContext();
        int count = 0;
        if(context.getAttribute("onLineCount")!=null){
            count = Integer.parseInt(String.valueOf(context.getAttribute("onLineCount")));
        }
        context.setAttribute("onLineCount", --count);
        logger.info("监听Session销毁了，当前在线人数为："+count );
    }
    
    
}
