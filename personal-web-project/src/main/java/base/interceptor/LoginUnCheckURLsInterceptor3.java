package base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import base.entiy.User;

/**
 * 处理session超时的拦截器
 * 
 * 另外，你要在controller中设置session的超时时间
 * request.getSession().setMaxInactiveInterval(20);//20秒
 * request.getSession().setAttribute("activeUser", activeUser);
 * 
 */
public class LoginUnCheckURLsInterceptor3 implements HandlerInterceptor {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *  配置不需要拦截的URL资源，这里是一个数组，应该也可以是一个list对象
     */
    public String[] unCheckURLs;

    public String[] getUnCheckURLs() {
        return unCheckURLs;
    }

    public void setUnCheckURLs(String[] unCheckURLs) {
        this.unCheckURLs = unCheckURLs;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object arg2) throws Exception {
        
        // 获取系统session
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute("activeUser");
        User activeUser = null;
        //进行类型判断
        if(attribute!=null && attribute instanceof User ){
            // 返回true，则这个方面调用后会接着调用postHandle(), afterCompletion()
            activeUser = (User) session.getAttribute("activeUser");
        }
        
        if (activeUser != null) {
            // 已经登录则放行
            return true;
        } else {
            // TODO 未登录 ,则抛出异常，交给全局异常处理器去处理异常信息
            //throw new SessionTimeoutException("");
        }
        
        // 获取访问路径/simpleUrl/0.htm
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
        System.out.println(requestUrl);
        if (null != unCheckURLs && unCheckURLs.length >= 1){
            for (String unCheckURL : unCheckURLs) {
                if (requestUrl.contains(unCheckURL)) {
                    return true;
                }
                //方式2：等价判断
                if (requestUrl.indexOf(unCheckURL) >= 0) {
                    return true;
                }
            }
        }
        
        // TODO 更好的实现方式的使用cookie进行校验等操作
        
        // 都需要登录后才能访问
        // 方式1：重定向到指定登录页面,该页面不能存在于WEN-INF下面吗？
        //response.sendRedirect(request.getContextPath() + "/base/login");
        // 方式2：请求转发到指定登录页面
        request.getRequestDispatcher("/WEB-INF/jsp/base/login.jsp").forward(request,response);  
        
        return false;
    }

    
    @Override
    public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,Object arg2, ModelAndView arg3) throws Exception {
    }

}
