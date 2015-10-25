package base.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import base.entiy.User;

/**
 * 自动登录校验的过滤器（登陆后，加载session中的购物车信息）
 * 
 * @author Liubao
 * @2014年11月26日
 * 
 */
@SuppressWarnings("deprecation")
public class AutoLoginFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @SuppressWarnings("unused")
    public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
        
      //测试代码开始=====================================
        
        //request对象的2种获取方式
        HttpServletRequest request1 = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletRequest request2 = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        //HttpServletRequest request2 = ServletActionContext.getRequest();
        
        //获取ServletContext对象的2种方式
        ServletContext servletContext1 = request1.getSession().getServletContext();
        //ServletContext servletContext2 = ServletActionContext.getServletContext();
        
        
        //获取spring上下文的4种方式！ 
        WebApplicationContext applicationContext1 = RequestContextUtils.getWebApplicationContext(request1);
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
        HttpSession session1 = request1.getSession(true);
        //方式2
        HttpSession session2 = request1.getSession();
        
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
        //response1.addCookie(cookie);
        
        //测试代码结束=====================================
        
        
        HttpServletRequest request;
        HttpServletResponse response;
        try {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) resp;
        } catch (Exception e) {
            throw new RuntimeException("Is not a HTTP request");
        }

        // Session中保存的账号信息,其中包含了账号和密码,密码是经过加密处理的了;
        HttpSession session = request.getSession();

        // 获取user的账户名和密码的cookie信息,下面同步有获取该账号的购物车的信息;
        User loginUser = (User) session.getAttribute("user");
        // sUser为空,表示用户还没有登录;
        if (loginUser == null) {
            Cookie cs[] = request.getCookies();
            for (int i = 0; cs != null && i < cs.length; i++) { 
                // cookie中的数据(只要有email,就可以查询出其对应的数据库中的密码;)
                if ("userInfo".equals(cs[i].getName())) {
                    String userInfo = loginUser.getId()
                            + loginUser.getUsername() + "_"
                            + loginUser.getPassword() + "_"
                            + loginUser.getEmail() + "_"
                            + loginUser.getBirthday();
                    // 编码中涉及到中文,在网络间传输的时候,需要进行url编码;
                    userInfo = URLDecoder.decode(cs[i].getValue(), "utf-8");
                    int id = Integer.parseInt(userInfo.split("\\_")[0]);
                    String username = userInfo.split("\\_")[1];
                    String password = userInfo.split("\\_")[2];
                    String email = userInfo.split("\\_")[3];
                    String birthday = userInfo.split("\\_")[4];

                    // 根据唯一表示emainl地址等信息，获取数据库中对应的user对象;
                    // UserDao userDao = new UserDaoImpl();
                    // User findUser = userDao.findUser(email);

                    // 这是查询到的数据库中的经过MD5加密的密码
                    // String Spassword = findUser.getPassword();
                    String Spassword = "11111";

                    System.out.println("在自动过滤程序中--从Cookie[" + cs[i].getName()+ "]中直接获取的数据并输出为:" + cs[i].getValue());

                    System.out.println("Cookie中获取:"+ new String(cs[i].getValue().getBytes("iso-8859-1"), "utf-8")); //

                    if (Spassword.equals(password)) {
                        User user1 = new User();
                        user1.setId(id);
                        user1.setEmail(email);
                        user1.setPassword(password);
                        user1.setUsername(username);
                        user1.setBirthday(new Date());
                        request.getSession().setAttribute("user", user1);

                        //TODO 在这个位置将该客户的session对象中的购物车cart信息取出来

                        break;
                    }
                }
            }
        }
        // 放行拦截器继续执行
        chain.doFilter(request, response);
    }

    /**
     * 销毁方法
     */
    public void destroy() {

    }

}
