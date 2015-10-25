package browerchecker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class TestBrower {

    @SuppressWarnings("null")
    @Test
    public void testName() throws Exception {
        HttpServletRequest request=null;
        HttpServletResponse response=null;
        
        String header = request.getHeader("USER-AGENT");
        
        response.getWriter().write(header);
        
    }

}
