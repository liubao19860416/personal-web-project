package base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.meidusa.fastjson.JSON;
import com.meidusa.fastjson.JSONObject;

import static org.junit.Assert.*;
/**
 * 几个可以使用静态导包方式引入的参数方法
 */
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.junit.Assert.*;

/**
 * IndexController测试类
 * 
 * @author Liubao
 * @2014年12月14日
 * 
 */
public class MyControllerTest extends ControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private static String url = "/login";

    @Test
    public void testCouponService2() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", 560047);
        params.put("orderCode", "201412031300054056");
        
        //JSON字符串格式化方式
        JSONObject.toJSONString(params);
        JSON.toJSONString(params);
        
        //自定义的结果校验匹配器
        //Matcher matcher = new CouponViewObjectMatcher();
        
        mockMvc.perform(
                MockMvcRequestBuilders.post(url + "/test/coupon/").header("content-type",
                        CONTENT_TYPE_JSON).content(JSON.toJSONString(params)))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType(CONTENT_TYPE_JSON))
                        .andExpect(MockMvcResultMatchers.content().encoding(CONTENT_ENCODE))
                        //.andExpect(MockMvcResultMatchers.content().string(matcher))
                        //.andExpect(MockMvcResultMatchers.content().string(JSON.toJSONString(viewObjects)))
                        ;
        
    }
    

/**
 * 自定义的内部类，结果校验匹配器
 * @author Liubao
 * @2014年12月14日
 *
 */
/*class CouponViewObjectMatcher extends MyBaseMatcher<CouponViewObjectMatcher, CouponViewObject> {

        @Override
        protected void checkResponseViewObjects(List<CouponViewObject> couponViewObjects) {

            assertFalse(couponViewObjects.isEmpty());
            assertTrue(couponViewObjects.size() == 11);

            CouponViewObject couponViewObject = couponViewObjects.get(0);
            assertEquals("323", couponViewObject.getCode());

            CouponViewObject couponViewObject2 = couponViewObjects.get(1);

            assertTrue(Float.valueOf(couponViewObject2.getMoneyAmount()) >= Float
                    .valueOf(couponViewObject.getMoneyAmount()));

            assertTrue(Integer.valueOf(couponViewObject2.getFitToMaxDaysUsed()) >= Integer
                    .valueOf(couponViewObject2.getFitToMinDaysUsed()));
        }
    }*/
}
