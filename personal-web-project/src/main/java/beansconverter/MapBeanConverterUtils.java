package beansconverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Map和对象bean互相转化的工具类<br>
 */
public class MapBeanConverterUtils extends org.apache.commons.collections.MapUtils {

    /**
     * 将Map转换为Object
     */
    public static <T, V> T mapToObject(Class<T> clazz, Map<String, V> map) throws Exception {
        T object = clazz.newInstance();
        return mapToObject(object, map);
    }


    /**
     * 将Map转换为Object
     */
    public static <T, V> T mapToObject(T object, Map<String, V> map) throws Exception {
        return toObject(object, map, false);
    }
    
    /**
     * 将Map转换为Object
     */
    public static <T, V> T mapToObject(Class<T> clazz, Map<String, V> map,boolean toCamelCase) throws Exception {
        T object = clazz.newInstance();
        return toObject(object, map, toCamelCase);
    }

    /**
     * 将Map转换为Object
     */
    private static <T, V> T toObject(T object, Map<String, V> map,boolean toCamelCase) throws Exception {
        if (toCamelCase){
            map = toCamelCaseStringMap(map);
        }
        BeanUtils.populate(object, map);
        return object;
    }
    
    
    //=================================互相转换======================================
    
    
    /**
     * 直接将对象属性转换到Map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> object2Map(Object object)throws Exception {
        return BeanUtils.describe(object);
    }
    
    /**
     * 转换为Collection,同时为字段做驼峰转换或者下划线命名转换，或者不做任何操作，直接属性转换
     */
    public static <T> Collection<Map<String, String>> toMapListForFlat(Collection<T> collection) throws Exception {
        List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
        if (collection != null && !collection.isEmpty()) {
            for (Object object : collection) {
                Map<String, String> map = object2Map(object);
                //Map<String, String> map = toUnderlineMap(object);
                //Map<String, String> map2 = toCamelCaseMap(object);
                retList.add(map);
            }
        }
        return retList;
    }

    /**
     * 转换成Map并提供字段命名驼峰转下划线
     */
    public static Map<String, String> toUnderlineMap(Object object)throws Exception{
        Map<String, String> map = object2Map(object);
        return toUnderlineStringMap(map);
    }
    
    /**
     * 转换成Map并提供字段命名驼峰转下划线
     */
    public static Map<String, String> toCamelCaseMap(Object object)throws Exception{
        Map<String, String> map = object2Map(object);
        return toCamelCaseStringMap(map);
    }

    /**
     * 将Map的Keys转译成下划线格式的<br>
     * (例:branchNo -> branch_no)<br>
     */
    public static <V> Map<String, V> toUnderlineStringMap(Map<String, V> map) {
        Map<String, V> newMap = new HashMap<String, V>();
        for (String key : map.keySet()) {
            //newMap.put(JavaBeanUtil.toUnderlineString(key), map.get(key));
            safeAddToMap(newMap,JavaBeanUtil.toUnderlineString(key), map.get(key));
        }
        return newMap;
    }
    
    /**
     * 将Map的Keys去下划线<br>
     * (例:branch_no -> branchNo )<br>
     */
    public static <V> Map<String, V> toCamelCaseStringMap(Map<String, V> map) {
        Map<String, V> newMap = new HashMap<String, V>();
        for (String key : map.keySet()) {
           //newMap.put(JavaBeanUtil.toCamelCaseString(key), map.get(key));
           safeAddToMap(newMap, JavaBeanUtil.toCamelCaseString(key),map.get(key));
        }
        return newMap;
    }


}
