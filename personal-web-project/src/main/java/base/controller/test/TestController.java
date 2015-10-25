package base.controller.test;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试Controller
 * 
 * @author Liubao
 * @2014年12月1日
 * 
 */
@Controller
@RequestMapping(value ="/testController")
public class TestController {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private TestService testService;
    
    @Resource
    private TestDao testDao;

    @RequestMapping(value = "/testController")
    public void testController(HttpServletResponse response, Integer id) throws Exception {
        switch (id) {
        case 1:
            throw new TestException("10", "controller10");
        case 2:
            throw new TestException("20", "controller20");
        case 3:
            throw new TestException("30", "controller30");
        case 4:
            throw new TestException("40", "controller40");
        case 5:
            throw new TestException("50", "controller50");
        default:
            throw new ParameterException("Controller Parameter Error");
        }
    }

    @RequestMapping(value = "/testService")
    public void testService(HttpServletResponse response, Integer id) throws Exception {
        testService.exception(id);
        testService.testDao(id);
    }

    @RequestMapping(value = "/testDao")
    public void testDao(HttpServletResponse response, Integer id) throws Exception {
        testDao.exception(id);
    }
}