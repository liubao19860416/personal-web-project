package base.filter.per;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SesitiveFilter implements Filter {

    @Override
    public void doFilter(HttpServletRequest request,
            HttpServletResponse response, Filter chain) {
        request.getQueryString().replace("被就业", "就业").replace("敏感", "");
        chain.doFilter(request, response, chain);
    }

}
