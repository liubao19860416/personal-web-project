package beansconverter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 * @Description 对象属性操作工具类 java将对象列表中的某个属性转换成List或Map
 */
public final class BeanPropertiesUtil {

    /**
     * 根据对象列表和对象的某个属性,返回list中所有对象的该属性的List集合
     */
    public static <T> List<Object> getBeanProperty2List(List<T> objList,String beanPropertyName) throws Exception {
        if (objList == null || objList.size() == 0)
            throw new IllegalArgumentException("No objList specified");
        if (beanPropertyName == null || "".equals(beanPropertyName)) {
            throw new IllegalArgumentException(
                    "No propertyName specified for bean class '"
                            + objList.get(0).getClass() + "'");
        }
        PropertyUtilsBean p = new PropertyUtilsBean();
        List<Object> propList = new LinkedList<Object>();
        for (int i = 0; i < objList.size(); i++) {
            T obj = objList.get(i);
            propList.add(p.getProperty(obj, beanPropertyName));
        }
        return propList;
    }

    /**
     * 将List列表中的对象的某个属性封装成一个Map对象，key值是属性名，
     * value值是对象列表中对象属性值的列表List<Object>
     * 可变参数，可以使用数组代替
     */
    public static <T> Map<String, List<Object>> getBeanProperties2Map(List<T> objList, String... beanPropertyNames) throws Exception {
        if (objList == null || objList.size() == 0){
            throw new IllegalArgumentException("No objList specified");
        }
        if (beanPropertyNames == null || beanPropertyNames.length == 0) {
            throw new IllegalArgumentException("No propertyName specified for bean class '"
                            + objList.get(0).getClass() + "'");
        }
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        for (int i = 0; i < beanPropertyNames.length; i++) {
            map.put(beanPropertyNames[i], getBeanProperty2List(objList, beanPropertyNames[i]));
        }
        return map;
    }
    
    /**
     * 拷贝对象属性
     */
    public static void copyProperties(Object dest, Object orig) throws Exception {
            BeanUtilsBean.getInstance().copyProperties(dest, orig);
    }
    
    /** 
     * 将源对象中的值覆盖到目标对象中，仅覆盖源对象中不为NULL值的属性 ；
     * 仅覆盖目标对象中存在key的值
     */  
    public static void copyProperties(Object orig, Map<?, ?> dest) throws Exception {  
        Field[] fields = orig.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();
            if (!dest.containsKey(name)) {
                if (PropertyUtils.isReadable(orig, name)) {
                    Object value = PropertyUtils.getSimpleProperty(orig, name);
                    if (value != null) {
                        PropertyUtils.setSimpleProperty(dest, name, value);
                    }
                }
            }
        }
    }  
    
    /**
     * 拷贝Map对象属性到bean对象中,只拷贝不为空属性
     * 或者将拷贝普通对象属性到list对象中
     */
    public static void copyPropertiesNotNulFromMapToObject(Object dest, Object orig) throws Exception {
        if (orig instanceof Map) {
            //拷贝Map对象属性到bean对象中；orig是Map对象，dest是普通对象
            Iterator<?> names = ((Map<?, ?>) orig).keySet().iterator();
            while (names.hasNext()) {
                String name = (String) names.next();
                if (PropertyUtils.isWriteable(dest, name)) {
                    Object value = ((Map<?, ?>) orig).get(name);
                    if (value != null) {
                        PropertyUtils.setSimpleProperty(dest, name, value);
                    }
                }
            }
        } else if(dest instanceof List){
                //拷贝普通对象属性到list对象中，orig是普通对象，dest是List对象
                dest=(List<String>)dest;
                Field[] fields = orig.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    String name = fields[i].getName();
                    if ("serialVersionUID".equalsIgnoreCase(name)) {
                        continue;
                    }
                    ((List<String>) dest).add(name);
                }
            }else if(dest.getClass().isArray()){
                //拷贝普通对象属性到list对象中；orig是普通对象，dest是数组对象
                Field[] fields = orig.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    String name = fields[i].getName();
                        Object value = PropertyUtils.getSimpleProperty(orig, name);
                        if (value != null) {
                            PropertyUtils.setSimpleProperty(dest, name, value);
                    }
                }
            }else{
                //其他情况；orig是普通对象，dest是普通对象
                Field[] fields = orig.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    String name = fields[i].getName();
                    if (PropertyUtils.isReadable(orig, name)&& PropertyUtils.isWriteable(dest, name)) {
                        Object value = PropertyUtils.getSimpleProperty(orig, name);
                        if (value != null) {
                            PropertyUtils.setSimpleProperty(dest, name, value);
                        }
                    }
                }
            }
    }
    
    // hide from initiating
    private BeanPropertiesUtil() {
    }
}