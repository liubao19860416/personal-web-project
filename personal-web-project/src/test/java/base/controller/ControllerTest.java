package base.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 几个可以使用静态导包方式引入的参数方法，在子类中进行引入
 */
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 这里加载的文件，做好给他们一个顺序加载，可能会比较合适
 * 
 * @author Liubao
 * @2014年12月14日
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration( locations = { 
    /*"file:D:/git/pro/grape-service20/src/main/webapp/WEB-INF/applicationContext-venus-server.xml"*/
	/* "file:D:/eclipse-20141015/workspace/project-poi/src/test/resources/test/test-*.xml" */
   /* "classpath:test/test-*.xml",*/
    "classpath:test/test-springmvc.xml",
    "classpath:test/test-placeholder.xml",
    "classpath:test/test-dataSource.xml",
    "classpath:test/test-mybatis.xml",
    "classpath:test/test-tx2.xml",
})
@WebAppConfiguration
public class ControllerTest {
    
    public final static String CONTENT_TYPE_JSON = 
            "application/json;charset=UTF-8";
    
    public final static String CONTENT_ENCODE = "UTF-8";
    
    @Autowired  
    protected WebApplicationContext webAppContext;  
    
    protected MockMvc mockMvc;  

    @Before
    public void init() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup( webAppContext )
                .build(); 
    }

    @After
    public void down() throws Exception {
    }

    @Test
    public void testENVISOK() throws Exception {
        Assert.assertTrue(true);
    }
}
