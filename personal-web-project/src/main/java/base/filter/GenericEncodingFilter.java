package base.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解决get和post请求 全部乱码
 * 
 */
public class GenericEncodingFilter implements Filter {

    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 获取初始化参数!
    private FilterConfig filterConfig;
    private String encoding = "UTF-8";

    public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        try {
            // 转型为与协议相关对象
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) resp;
        } catch (Exception e) {
            throw new RuntimeException("Is Not A HTTP Request Or Response!");
        }
        // 基础过滤
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=" + encoding);

        // 对request包装增强
        HttpServletRequest myrequest = new MyRequest(request);
        // 包装完成之后,放行
        chain.doFilter(myrequest, response);
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String value = this.filterConfig.getInitParameter("encoding");
        if (value != null) {
            encoding = value;
        }
    }

}

/**
 * 自定义request包装对象
 */
class MyRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    private boolean hasEncode = false;

    public MyRequest(HttpServletRequest request) {
        super(request);// super必须写
        this.request = request;
    }

    // 对需要增强方法 进行覆盖
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, String[]> getParameterMap() {
        // 先获得请求方式
        String method = request.getMethod();
        if ("get".equalsIgnoreCase(method)) {
            // get请求
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (!hasEncode) { // 确保get手动编码逻辑只运行一次
                for (String parameterName : parameterMap.keySet()) {
                    String[] values = parameterMap.get(parameterName);
                    if (values != null) {
                        for (int i = 0; i < values.length; i++) {
                            try {
                                // 处理get乱码
                                values[i] = new String(values[i].getBytes("ISO-8859-1"),super.getCharacterEncoding());
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                hasEncode = true;
            }
            return parameterMap;
        }
        return super.getParameterMap();
    }

    @Override
    public String getParameter(String name) {
        // 这里循环调用,可以减少代码的编写量!
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        if (values == null) {
            return null;
        }
        // 取回参数的第一个值
        return values[0]; 
    }

    @Override
    public String[] getParameterValues(String name) {
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        return values;
    }

}
