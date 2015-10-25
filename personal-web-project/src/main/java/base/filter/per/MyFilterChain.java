package base.filter.per;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 将过滤链定义为实现Filter接口,灵活性更好
 * 
 * @author Liubao
 * @2015年5月11日
 * 
 */
public class MyFilterChain implements Filter {
    List<Filter> filters = new ArrayList<Filter>();
    int index = 0;

    public MyFilterChain addFilter(Filter f) {
        this.filters.add(f);
        return this;
    }

    @Override
    public void doFilter(HttpServletRequest request,
            HttpServletResponse response, Filter chain) {
        if (index == filters.size())
            return;
        Filter f = filters.get(index);
        index++;
        f.doFilter(request, response, chain);
    }
}
