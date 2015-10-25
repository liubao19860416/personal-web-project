package beansconverter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sql.SqlUtil;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author liuli
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public final class BeanUtil {
    /**
     * 修改日志记录的日志
     */
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 构造方法
     */
    private BeanUtil() {

    }

    public static Object convertObject(Object entity, Class entityClass) {
        Object obj = null;
        try {
            obj = entityClass.newInstance();
            Method[] methods = entityClass.getMethods();
            Method[] methodsCar = entity.getClass().getMethods();
            for (Method method : methods) {
                if (SqlUtil.isGetMethod(method)) {
                    for (Method methodCar : methodsCar) {
                        if (method.getName().equals(methodCar.getName())) {
                            if (SqlUtil.checkEntityValue(methodCar, entity)) {
                                SqlUtil.invokeMethod(obj, SqlUtil.setByGet(methodCar),
                                        SqlUtil.getEntityValue(methodCar, entity));
                            }
                        }
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("convertObject{} exception is: " + e.getMessage());

        }
        return obj;
    }

    /**
     * 
     * 功能描述: 解析bean，返回List<Map<String, Object>>集合 Map 的 key 是 bean 属性名称，value 是bean 属性值 list包含三个map，可按索引进行索引。1：intMap，
     * 2：varcharMap， 3：decimalMap
     * 
     * @param bean 对象
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> beanPropertiesToList(Object bean) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> intMap = new HashMap<String, Object>();
        Map<String, Object> varcharMap = new HashMap<String, Object>();
        Map<String, Object> decimalMap = new HashMap<String, Object>();

        Field[] fields = bean.getClass().getDeclaredFields();
        Field.setAccessible(fields, true);
        int length = fields.length;
        try {
            for (int i = 0; i < length; i++) {
                Field field = fields[i];
                String fieldType = field.getType().toString();
                Object fieldValue = field.get(bean);
                String fieldName = field.getName();

                if ((fieldType.equals("class java.lang.Integer") || fieldType.equals("class java.lang.Long"))
                        && fieldValue != null) {
                    intMap.put(fieldName, fieldValue);
                } else if (fieldType.equals("class java.lang.String") && fieldValue != null) {
                    varcharMap.put(fieldName, fieldValue);
                } else if (fieldType.equals("class java.math.BigDecimal") && fieldValue != null) {
                    decimalMap.put(fieldName, fieldValue);
                }
            }
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());

        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());

        }
        list.add(intMap);
        list.add(varcharMap);
        list.add(decimalMap);
        return list;
    }

    /**
     * 
     * 把字符串转化成相应表名
     * 
     * @param str 字符串
     * @return String(tableName) 表名
     */
    public static String convertStrToTabName(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder("t");
        for (char c : chars) {
            String temp = "";
            if (!Character.isLowerCase(c)) {
                temp = "_" + Character.toLowerCase(c);
            } else {
                temp = c + "";
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param str 字符
     * @return 字符串
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String convertStrToIdName(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            String temp = "";
            if (!Character.isLowerCase(c)) {
                temp = "_" + Character.toLowerCase(c);
            } else {
                temp = c + "";
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param source
     * @param destinationClass
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> List<T> copyToList(List<?> source, Class<T> destinationClass) {
        if (source == null || source.size() == 0) {
            return Collections.emptyList();
        }
        List<T> res = new ArrayList<T>(source.size());
        try {
            for (Object o : source) {
                T t;
                t = destinationClass.newInstance();
                BeanUtils.copyProperties(t, SqlUtil.invokeDate(o));
                res.add(t);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error("copyToList{} exception is: " + e.getMessage());

        }
        return res;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param source
     * @param destinationClass
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> List<T> copyToList(Map<Long, ?> source, Class<T> destinationClass) {
        if (source == null || source.size() == 0) {
            return Collections.emptyList();
        }
        List<T> res = new ArrayList<T>(source.size());
        try {
            for (Map.Entry<Long, ?> entry : source.entrySet()) {
                T t;
                t = destinationClass.newInstance();
                BeanUtils.copyProperties(t, SqlUtil.invokeDate(entry.getValue()));
                res.add(t);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error("copyToList{} exception is: " + e.getMessage());

        }
        return res;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param entity
     * @param destinationClass
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> T copyToObject(Object entity, Class<T> destinationClass) {
        if (entity == null) {
            return null;
        }
        T t = null;
        try {
            t = destinationClass.newInstance();
            BeanUtils.copyProperties(t, SqlUtil.invokeDate(entity));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error("copyToObject{} exception is: " + e.getMessage());

        }
        return t;
    }

    public static List<Map<String, Object>> transBean2List(List<Object> list) {
        if (list == null)
            return null;
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        for (Object obj : list) {
            listMap.add(transBean2Map(obj));
        }
        return listMap;
    }

    public static Map<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {// 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            logger.error("transBean2Map Error " + e);
        }
        return map;
    }

}
