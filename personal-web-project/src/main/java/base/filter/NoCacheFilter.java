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
 * 不需要缓存的过滤器
 * 
 * @author Liubao
 * @2014年11月26日
 * 
 */

public class NoCacheFilter implements Filter {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request;
        HttpServletResponse response;
        try {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) resp;
        } catch (Exception e) {
            throw new RuntimeException("Is not a HTTP request");
        }
        
        response.setDateHeader("Expires", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void destroy() {

    }

}
