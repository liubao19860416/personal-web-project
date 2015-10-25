package sql;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author liuli
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SqlUtil {

    private static Logger logger = LoggerFactory.getLogger(SqlUtil.class);

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param entityClass
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getQueryColums(Class<?> entityClass) {
        Method[] methods = entityClass.getMethods();
        StringBuffer columnBuffer = new StringBuffer();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Column.class)) {
                /**
                 * append annotation (column) to StringBuffer
                 */
                columnBuffer.append(getColumnName(method));
                /**
                 * if this method is last ,not append comma,else append comma
                 */
                columnBuffer.append(",");
            }
        }
        return columnBuffer.deleteCharAt(columnBuffer.lastIndexOf(",")).toString();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param entityClass
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getTableName(Class<?> entityClass) {
        return entityClass.getAnnotation(Entity.class).name();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param method
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getColumnName(Method method) {
        return method.getAnnotation(Column.class).name();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param method
     * @param entity
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Object getEntityValue(Method method, Object entity) {
        try {
            return method.invoke(entity, null);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            logger.error("getEntityValue{} exception is: " + e.getMessage());
        }
        return null;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param entity
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Object getPrimaryKeyValue(Object entity) {
        return getEntityValue(getPrimaryKeyMethod(entity.getClass().getMethods()), entity);
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param methods
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Method getPrimaryKeyMethod(Method[] methods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Id.class)) {
                return method;
            }
        }
        return null;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param methods
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getPrimaryKeyColumn(Method[] methods) {
        return getColumnName(getPrimaryKeyMethod(methods));
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param methods
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getPrimaryKeyMethodName(Method[] methods) {
        return getPrimaryKeyMethod(methods).getName();
    }

    /**
     * 
     * @param entityClass
     * @return
     */
    public static boolean checkEntity(Class<?> entityClass) {
        return entityClass.isAnnotationPresent(Entity.class) ? true : false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param entityClass
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getPrimaryKeySetFunction(Class<?> entityClass) {
        if (!checkEntity(entityClass)) {
            return null;
        }
        return new StringBuffer().append("set").append(entityClass.getSimpleName()).append("Id").toString();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param entity
     * @param methodName
     * @param param
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean invokeMethod(Object entity, String methodName, Object param) {
        try {
            entity.getClass().getMethod(methodName, param.getClass()).invoke(entity, param);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            logger.error("invokeMethod{} entity{" + entity + "} methodName{" + methodName + "} param{" + param
                    + "} exception is:" + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param method
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isGetMethod(Method method) {
        return method.getName().startsWith("get") && !method.getName().equals("getClass") ? true : false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param method
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isSetMethod(Method method) {
        return method.getName().startsWith("set") ? true : false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param method
     * @param entity
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkEntityPrimary(Method method, Object entity) {
        if (!method.isAnnotationPresent(Id.class)) {
            return false;
        }
        return getEntityValue(method, entity) == null ? false : true;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param method
     * @param entity
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean checkEntityValue(Method method, Object entity) {
        return getEntityValue(method, entity) == null ? false : true;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param method
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isEntityType(Method method) {
        return method.getReturnType().getPackage().getName().equals("com.saic.ebiz.csc.service.entity") ? true : false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param method
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String setByGet(Method method) {
        if (isGetMethod(method)) {
            return method.getName().replaceAll("get", "set");
        }
        return null;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param method
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isDateType(Method method) {
        return method.getReturnType().getSimpleName().equals("Date") ? true : false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param method
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isBigDecimalType(Method method) {
        return method.getReturnType().getSimpleName().equals("BigDecimal") ? true : false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param entity
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Object invokeDate(Object entity) {
        Method[] methods = entity.getClass().getMethods();
        for (Method method : methods) {
            if (SqlUtil.isGetMethod(method)) {
                if (isDateType(method)) {
                    if (!checkEntityValue(method, entity)) {
                        invokeMethod(entity, SqlUtil.setByGet(method), new Date());
                    }
                }
                if (isBigDecimalType(method)) {
                    if (!checkEntityValue(method, entity)) {
                        invokeMethod(entity, SqlUtil.setByGet(method), new BigDecimal(1));
                    }
                }
            }
        }
        return entity;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param entity
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void setNull(Object entity) {
        Method[] methods = entity.getClass().getMethods();
        for (Method method : methods) {
            if (SqlUtil.isGetMethod(method)) {
                if (!checkEntityValue(method, entity)) {
                    invokeMethod(entity, SqlUtil.setByGet(method), "");
                }
            }
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param method
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getProperty(Method method) {
        char[] chars = new char[1];
        chars[0] = method.getName().substring(3).charAt(0);
        String temp = new String(chars);
        return method.getName().substring(3).replaceFirst(temp, temp.toLowerCase());
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param method
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isPaginationFunction(Method method) {
        return !method.getName().equals("getPagination") ? true : false;
    }

}
