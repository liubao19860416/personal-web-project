package base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 需要缓存的过滤器
 * 
 * @author Liubao
 * @2014年11月26日
 * 
 */

public class NeedCacheFilter implements Filter {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private FilterConfig filterConfig;

    public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request;
        HttpServletResponse response;
        try {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) resp;
        } catch (Exception e) {
            throw new RuntimeException("Is not a HTTP request");
        }
        
        long time = 0;
        // /day21_01_filterExample1/index.html
        String uri = request.getRequestURI();
        System.out.println(uri);

        String extendName = uri.substring(uri.lastIndexOf(".") + 1);
        if ("html".equals(extendName)) {
            String value = filterConfig.getInitParameter("html");
            time = Long.parseLong(value) * 60 * 60 * 1000;
        }
        if ("css".equals(extendName)) {
            String value = filterConfig.getInitParameter("css");
            time = Long.parseLong(value) * 60 * 60 * 1000;
        }
        if ("js".equals(extendName)) {
            String value = filterConfig.getInitParameter("js");
            time = Long.parseLong(value) * 60 * 60 * 1000;
        }

        response.setDateHeader("Expires", System.currentTimeMillis() + time);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {

    }

}
