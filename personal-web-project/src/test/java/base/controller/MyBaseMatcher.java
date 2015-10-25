package base.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import base.controller.result.ResultInfo;

import com.meidusa.fastjson.JSON;
import com.meidusa.fastjson.JSONObject;

/**
 * 测试用例Matcher抽象基类，用来对response JSON对象做检查
 */
public abstract class MyBaseMatcher<T extends MyBaseMatcher<T, V>, V> extends
        BaseMatcher<String> {

    private Class<T> matcherClass;
    private Class<V> viewObjectClass;

    @SuppressWarnings("unchecked")
    public MyBaseMatcher() {
        Type[] types = ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments();
        this.matcherClass = (Class<T>) types[0];
        this.viewObjectClass = (Class<V>) types[1];
    }

    @Override
    public boolean matches(Object o) {
        String responseJSONString = (String) o;
        try {
            assertNotNull(responseJSONString);
            assertTrue(StringUtils.isNotEmpty(responseJSONString));
            
            List<? extends Object> listObj = JSON.parseArray(responseJSONString,viewObjectClass);
            //判断方式1，直接强转
            //List<V> listObj = (List<V>)JSON.parseArray(responseJSONString,viewObjectClass);
            List<V> resultObj = (List<V>) JSONObject.parseArray(responseJSONString, viewObjectClass);
            
            ResultInfo<V> resultInfo = new ResultInfo<V>(ResultInfo.RESULT_SUCCESS, 100, "OK");
            resultInfo.setDataResultList(resultObj);
            
            assertNotNull(resultObj);
            assertNotNull(resultInfo);
            assertEquals(100, resultInfo.getMessageCode());

            //判断方式2，类型判断
            List<V> viewObjs = new ArrayList<>();
            if (listObj instanceof List) {
                viewObjs = (List<V>) listObj;
            } else {
                viewObjs.add(transToViewObject((Map<String, Object>) listObj));
            }

            //检查返回的对象类型
            checkResponseViewObjects(viewObjs);
            
            return true;
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void describeMismatch(Object o, Description d) {
        d.appendText("invalid response >> " + o);
    }

    @Override
    public void describeTo(Description d) {
        d.appendValue(matcherClass);
    }

    /**
     * 自定义的详细的检查数据方法，在具体的思想中进行覆写即可 
     */
    protected abstract void checkResponseViewObjects(List<V> viewObjects);

    /**
     * 将对象转换为ViewObject单一对象格式数据
     */
    protected V transToViewObject(Map<String, Object> properties) throws Exception {
        V vo = newViewObject();
        BeanUtilsBean.getInstance().populate(vo, properties);
        return vo;
    }

    protected V newViewObject() {
        V obj = null;
        try {
            obj = this.viewObjectClass.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return obj;
    }

}
