package beansconverter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean2;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.entiy.User;

/**
 * 对实体bean做一些处理<br>
 * 〈功能详细描述〉
 */
public final class MyBeanUtil {
    
    //测试方法
    public static void main(String[] args) {
        String str = "userVelName";
        String tabName = MyBeanUtil.convertStrToTabName(str);
        System.out.println(tabName);
        String toIdName = MyBeanUtil.convertStrToIdName(str);
        System.out.println(toIdName);
        User user=new User();
        user.setId(1);
        user.setUsername("name");
        user.setPassword("111");
        List<Map<String, Object>> map = MyBeanUtil.beanPropertiesToList(user);
        for (Map<String, Object> map2 : map) {
            for (String key : map2.keySet()) {
                Object value = map2.get(key);
                System.out.println(key+"===>"+String.valueOf(value));
            }
        }
        
        
        MyBeanUtil.transNullToEmptyString(user);
        
        User user2=new User();
        MyBeanUtil.copyProperties(user2, user);
        Assert.assertNotNull(user2);
        
        
        
    }

    private static Logger logger = LoggerFactory.getLogger(MyBeanUtil.class);

    /**
     * 修改日志更新异常
     */
    public static final String AMEND_UPDATE_EXCEPTION = "修改日志更新异常";
    /**
     * 修改日志更新异常编码
     */
    public static final int AMEND_UPDATE_CODE = 30100008;
    
    /**
     * 注册自定义属性转换器
     */
    static {
        BeanUtilsBean.setInstance(new BeanUtilsBean( new CustomizedConvertUtilsBean() ) );
    }

    private MyBeanUtil() {

    }

    /**
     * 功能描述: 解析bean，返回List<Map<String, Object>>集合 Map 的 key 是 bean 属性名称，value
     * 是bean 属性值 list包含三个map，可按索引进行索引。1：intMap， 2：varcharMap， 3：decimalMap
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

                if ((fieldType.equals("class java.lang.Integer") || fieldType
                        .equals("class java.lang.Long")) && fieldValue != null) {
                    intMap.put(fieldName, fieldValue);
                } else if (fieldType.equals("class java.lang.String")
                        && fieldValue != null) {
                    varcharMap.put(fieldName, fieldValue);
                } else if (fieldType.equals("class java.math.BigDecimal")
                        && fieldValue != null) {
                    decimalMap.put(fieldName, fieldValue);
                }
            }
        } catch (IllegalArgumentException e) {
            logger.error(AMEND_UPDATE_EXCEPTION, e);
            throw new RuntimeException("", e);
        } catch (IllegalAccessException e) {
            logger.error(AMEND_UPDATE_EXCEPTION, e);
            throw new RuntimeException("", e);
        }
        list.add(intMap);
        list.add(varcharMap);
        list.add(decimalMap);
        return list;
    }

    /**
     * 把字符串转化成相应表名t_user_vel类似格式
     */
    public static String convertStrToTabName(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder("t_");
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
	 * 将驼峰命名转换为下划线
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
     * 将对应的对象中String格式的null属性值，转换为空字符串""
     */
    public static void transNullToEmptyString( Object target ) {
        if( target == null ) {
            return;
        }
        PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance().getPropertyUtils();
        PropertyDescriptor[] descriptors = propertyUtils.getPropertyDescriptors( target );
        try {
            if( descriptors != null ) {
                for( PropertyDescriptor desc : descriptors ) {
                    if( String.class.isAssignableFrom( desc.getPropertyType() ) ) {
                        Method readMethod = desc.getReadMethod();
                        Object value = readMethod.invoke( target );
                        if( value == null ) {
                            Method method = desc.getWriteMethod();
                            method.invoke( target, "" );
                        }
                    }
                }
            }
        }catch( Exception ex ) {
            logger.error( "GrapeBizUtils.transNullToEmptyString exception", ex );
        }
    }
    
    
    /**
     * 将指定对象的指定属性拷贝到对应对象的指定属性中去，可以添加指定的统一判断方法
     */
    public static void copyProperties( Object dest, Object orig ) {
            if( dest == null || orig == null ) {
                return;
            }
            try {
                transNullToEmptyString( orig );
                
                BeanUtilsBean.getInstance().copyProperties( dest, orig );
                
                //在这里可以统一进行类型判断，统一处理
                /*if( dest instanceof BaseEntity ) {
                    copyProperty( dest, "id", orig, "vid" );
                }*/
                
                transNullToEmptyString( dest );
                
            } catch ( Exception ex ) {
                throw new IllegalArgumentException( ex );
            }
        }

    /**
     * 将指定对象的指定属性拷贝到对应对象的指定属性中去
     */
    public static boolean copyProperty( Object dest,String destFieldName,
            Object orig,String origFieldName ) {
        boolean b = false;
        PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance().getPropertyUtils();
        Object origValue;
        try {
            origValue = propertyUtils.getProperty( orig, origFieldName );
            /* 
             * verify if dest object does take the field of name "destFieldName"
             */
            propertyUtils.getProperty( dest, destFieldName );
            
            Class<?> destPropertyClass = propertyUtils.getPropertyType( dest, destFieldName );
            
            if( origValue == null && destPropertyClass != null && String.class.isAssignableFrom( destPropertyClass ) ) {
                origValue = "";
            }
            
            propertyUtils.setProperty( dest, destFieldName, origValue );
            b = true;
        }catch( Exception ex ) {
        }
        return b;
    }
    

}
/**
 *  自定义的转换器，转换日期和枚举类型格式的数据
 */
class CustomizedConvertUtilsBean extends ConvertUtilsBean2 {
   private static final CustomizedStringConverter STRING_CONVERTER = new CustomizedStringConverter();
   private static final EnumConverter  ENUM_CONVERTER = new EnumConverter();
   private static final DatetimeConverter DATE_CONVERTER = new DatetimeConverter();
   @Override
   @SuppressWarnings({ "rawtypes" })
   public Converter lookup( Class pClazz ) {
       if( String.class.equals( pClazz ) ) {
           return STRING_CONVERTER;
       }else if( pClazz.isEnum() ) {
           return ENUM_CONVERTER;
       }else if( Date.class.isAssignableFrom( pClazz ) ) {
           return DATE_CONVERTER;
       }else {
           return super.lookup( pClazz );
       }
   }
}
/**
 * 返回枚举值的方法需要重写,自定义的转换器
 */
class EnumConverter extends AbstractConverter {
    @Override
    protected String convertToString( final Object pValue ) throws Throwable {
        return ( ( Enum<?> ) pValue ).toString();
    }
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Class getDefaultType() {
        return null;
    }
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Object convertToType( Class type, Object value )
            throws Throwable {
        if( value == null ) {
            return null;
        }
        /*return Constants.getEnumByValue( type, value.toString() );*/
        return  null;
    }
} 

class DatetimeConverter extends AbstractConverter {
    @Override
    protected String convertToString( final Object pValue ) throws Throwable {
        if(pValue==null ) {
            return "";
        }
        return new SimpleDateFormat().format( ( Date )pValue );
    }
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Class getDefaultType() {
        return null;
    }
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Object convertToType( Class type, Object value ) throws Throwable {
        if( value == null || "".equals( value ) ) {
            return new Date(System.currentTimeMillis());
        }
        return new SimpleDateFormat().parse( ( String )value );
    }
} 

class CustomizedStringConverter extends AbstractConverter {
    StringConverter strConverter = new StringConverter();
    @Override
    protected String convertToString( final Object pValue ) 
            throws Throwable {
        
        if( pValue != null ) {
            if( Timestamp.class.isAssignableFrom( pValue.getClass() ) ) {
                if( pValue==null ) {
                    return "";
                }
                return  (( Timestamp )pValue).toLocaleString() ;
            }else if( Date.class.isAssignableFrom( pValue.getClass() ) ) {
                if( pValue==null) {
                    return "";
                }
                return ( ( Date )pValue ).toLocaleString() ;
            }else if( Boolean.class.equals( pValue.getClass() ) ) {
                return Boolean.TRUE.equals( pValue ) ? "1":"0";
            }else {
                return pValue.toString();
            }
        }
        return null;
    }
    @Override
    protected Object convertToType(Class arg0, Object arg1)
            throws Throwable {
        return null;
    }
    @Override
    protected Class getDefaultType() {
        return null;
    }
}
