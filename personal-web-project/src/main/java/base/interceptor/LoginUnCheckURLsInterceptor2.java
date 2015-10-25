package base.interceptor;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import base.entiy.User;


/**
 * 根据放行的url参数，进行权限检查的拦截器
 * 
 * @author Liubao
 * @2014年11月26日
 * 
 */
public class LoginUnCheckURLsInterceptor2 extends HandlerInterceptorAdapter {

    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 免登入 免检查地址
     */
    private List<String> unCheckURLs;

    public List<String> getUnCheckURLs() {
        return unCheckURLs;
    }

    public void setUnCheckURLs(List<String> unCheckURLs) {
        this.unCheckURLs = unCheckURLs;
    }

    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception{

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
            // 已经登录则放行
            return true;
        }
        
        // 获取访问路径,/personal-web-project/simpleUrl/0.htm
        String requestUrl = request.getRequestURI();
        //requestUrl=requestUrl.replace(request.getContextPath(), "");///simpleUrl/0.htm
        //requestUrl=requestUrl.substring(0, requestUrl.indexOf("."));
        
        //在这里使用contains判断的方式不合适
        /*if (unCheckURLs.contains(requestUrl)) {
            return true;
        }*/ 
        for ( String unCheckUrl : unCheckURLs) {
            //方式2：等价判断
            if (requestUrl.contains(unCheckUrl)) {
                return true;
            } 
            //方式2：等价判断
            if (requestUrl.indexOf(unCheckUrl) >= 0) {
                // 如果请求的地址是公开地址则放行
                return true;
            }
        }
        
        // TODO 更好的实现方式的使用cookie进行校验等操作
        
        // 做拦截的事情
        // 方式1：重定向到指定登录页面,该页面不能存在于WEN-INF下面吗？
        //response.sendRedirect(request.getContextPath() + "/base/login");
        // 方式2：请求转发到指定登录页面
        request.getRequestDispatcher("/WEB-INF/jsp/base/login.jsp").forward(request, response);  
        
        return false;
    }
}