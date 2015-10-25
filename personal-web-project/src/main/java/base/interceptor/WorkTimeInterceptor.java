package base.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 *  可以看出，上面的代码重载了preHandle()方法，该方法在业务处理器处理请求之前被调用。在该方法中，首先获得当前的时间，
 *  判断其是否在openingTime和closingTime之间，如果在，返回true，这样才会调用业务控制器去处理该请求；
 *  否则直接转向一个页面，返回false，这样该请求就不会被处理。
 *  
 * @author Liubao
 * @2014年11月26日
 *
 */
public class WorkTimeInterceptor extends HandlerInterceptorAdapter {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 指定上班时间
    private int openingTime; 
    // 指定下班时间
    private int closingTime; 
    // 指定错误提示页面的URL
    private String outsideOfficeHoursPage;

    // 重写 preHandle()方法，在业务处理器处理请求之前对该请求进行拦截处理
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        Calendar cal = Calendar.getInstance();
        // 获取当前时间
        int hour = cal.get(Calendar.HOUR_OF_DAY); 
        // 判断当前是否处于工作时间段内
        if (openingTime <= hour && hour <= closingTime) { 
            return true;
        } else {
            // 返回提示页面
            // 方式1：重定向到指定登录页面,outsideOfficeHoursPage不包括项目名称
            //response.sendRedirect(request.getContextPath() + outsideOfficeHoursPage);
            //String contextPath = request.getContextPath();//personal-web-project
            //outsideOfficeHoursPage="/personal-web-project/outsideOfficeHours.jsp";
            // 方式1：重定向到指定登录页面,该页面不能存在于WEN-INF下面吗？
            //response.sendRedirect(outsideOfficeHoursPage);
            // 方式2：请求转发到指定登录页面
            request.getRequestDispatcher("/outsideOfficeHours.jsp").forward(request,response);  
            //request.getRequestDispatcher("/WEB-INF/jsp/base/login.jsp").forward(request,response);  
            return false;
        }
    }

    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object o, ModelAndView mav) throws Exception {
        System.out.println("postHandle");
    }

    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object o, Exception excptn) throws Exception {
        System.out.println("afterCompletion");
    }

    /**
     * 下面是set和get方法
     */
    public int getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(int openingTime) {
        this.openingTime = openingTime;
    }

    public int getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(int closingTime) {
        this.closingTime = closingTime;
    }

    public String getOutsideOfficeHoursPage() {
        return outsideOfficeHoursPage;
    }

    public void setOutsideOfficeHoursPage(String outsideOfficeHoursPage) {
        this.outsideOfficeHoursPage = outsideOfficeHoursPage;
    }

}
