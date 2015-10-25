package http;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class FormUtil {
    /**
     * 解析class list 直接解析不含有_直接propertyName
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List parseForm(Class clazz, HttpServletRequest request,
            int rows, String paramPrefix) {

        List list = new ArrayList();
        PropertyUtilsBean util = new PropertyUtilsBean();
        PropertyDescriptor[] props = util.getPropertyDescriptors(clazz);
        Map map = new HashMap();
        String fieldName = "";
        for (int i = 0; i < props.length; i++) {
            fieldName = props[i].getName();
            Object[] vals = request.getParameterValues(paramPrefix + fieldName);
            if (vals == null) {
                continue;
            }
            Stack stack = new Stack();
            for (int j = 0; j < vals.length; j++) {
                stack.push(vals[j]);
            }
            map.put(fieldName, stack);
        }
        try {
            for (int i = 0; i < rows; i++) {
                Object obj = clazz.newInstance();
                for (int m = 0; m < props.length; m++) {
                    Stack stack = (Stack) map.get(props[m].getName());
                    if (stack == null || stack.size() == 0) {
                        continue;
                    }

                    BeanUtils.setProperty(obj, props[m].getName(), stack.pop());
                }
                list.add(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException("parse Exception :" + e);
        }
        Collections.reverse(list);
        return list;

    }
}
