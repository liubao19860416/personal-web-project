package base.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import base.controller.result.ResourcesUtil;
import base.entiy.User;
/**
 * 通过读取不过滤的URL配置文件，登陆时，进行拦截器权限检查
 * 
 * 在anonymousUrls中的url地址，拦截器进行放行
 * 
 * @author Liubao
 * @2014年11月27日
 *
 */
public class LoginUnCheckURLsInterceptor1 implements HandlerInterceptor {

    /**
     * 进入Controller方法之前调用
     *  用于权限拦截，类似web中的filter
     */
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
            // 已经登录则放行
            return true;
        }

        // 获取公开地址
        List<String> unCheckURLs = ResourcesUtil.getResourceskeyList("anonymousUrls");

        //TODO 获取访问路径
        String requestUrl = request.getRequestURI();

        for (String unCheckUrl : unCheckURLs) {
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

        // 执行到这里，说明用户当前没有登录，需要用户登录
        // 方式1：重定向到指定登录页面,该页面不能存在于WEN-INF下面吗？
        //response.sendRedirect(request.getContextPath() + "/base/login");
        // 方式2：请求转发到指定登录页面
        //request.getRequestDispatcher("/WEB-INF/jsp/base/login.jsp").forward(request,response);  

        // 抛出一个需要登录异常信息，由全异常处理器统一执行
        //ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 106,null));
        
        return false;
    }

    /**
     * 执行action之后返回视图之前
     * 如果要给页面传一些公用的数据可在此方法中执行
     */
    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {

    }

    /**
     * 执行action之后调用此方法
     *  用于执行捕获异常，记录功能执行完成的时间点用于性能监视
     */
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
