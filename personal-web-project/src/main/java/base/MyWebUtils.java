package base;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import base.entiy.User;

import com.meidusa.fastjson.JSON;
/**
 * WEB相关的一些快捷获取操作方法(最新版-20150128)
 * 
 * @author Liubao
 * @2015年01月14日
 *
 */
public class MyWebUtils {
    
    private static Logger logger = LoggerFactory.getLogger(MyWebUtils.class);
    
    // boolean true 字符串表示,false 字符串表示
    public static final String BOOLEAN_TRUE_IN_STR = "1"; 
    public static final String BOOLEAN_FALSE_IN_STR = "0";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_DATE = "1970-01-01";
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIMESTAMP = "1970-01-01 00:00:00";
    
    public static final Map<String,String> weekMap = new HashMap<>();

    static {
        weekMap.put("周1", "周一");
        weekMap.put("周2", "周二");
        weekMap.put("周3", "周三");
        weekMap.put("周4", "周四");
        weekMap.put("周5", "周五");
        weekMap.put("周6", "周六");
        weekMap.put("周7", "周日");
    }
    
    private MyWebUtils() {
    }
    
    @SuppressWarnings({ "unused", "rawtypes", "unchecked", "deprecation" })
    public static void main(String[] args) {
        HttpServletRequest request=null;
        try {
            User br=new User();
            br.setId(null);
            MyWebUtils.transNullToEmptyString(br);
            
            System.out.println(new SimpleDateFormat(DEFAULT_TIMESTAMP).
                    parse(DEFAULT_TIMESTAMP).toLocaleString());
            
            List<User> list=new ArrayList<User>();
            for (int i = 0; i < 10; i++) {
                User br1=new User();
                if(i%2==0){
                    br1.setId(null);
                    br1.setBirthday(null);
                }else{
                    br1.setId(i);
                    br1.setBirthday(new Date());
                }
                list.add(br1);
            }
            MyWebUtils.transNullToEmptyString(list);
            
            Map map=new HashMap();
            map.put("username", null);
            map.put("password", 222);
            map.put("age", 44l);
            map.put("bir", new Date());
            System.out.println(JSON.toJSONString(map));
            MyWebUtils.transNullToEmptyString(map);
            System.out.println(JSON.toJSONString(map));
            
            System.out.println("ok...");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    /** 
     * 将url参数转换成map 
     * @param param aa=11&bb=22&cc=33 
     */  
    public static Map<String, Object> getUrlParams(String param) {  
        Map<String, Object> map = new HashMap<String, Object>(0);  
        if (StringUtils.isBlank(param)) {  
            return map;  
        }  
        String[] params = param.split("&");  
        for (int i = 0; i < params.length; i++) {  
            String[] p = params[i].split("=");  
            if (p.length == 2) {  
                map.put(p[0], p[1]);  
            }  
        }  
        return map;  
    }  
    
    /** 
     * 将map转换成url格式 aa=11&bb=22&cc=33 
     * @param map 
     */  
    public static String getUrlParamsByMap(Map<String, Object> map) {  
        if (map == null) {  
            return "";  
        }  
        StringBuffer sb = new StringBuffer();  
        for (Map.Entry<String, Object> entry : map.entrySet()) {  
            sb.append(entry.getKey() + "=" + entry.getValue());  
            sb.append("&");  
        }  
        String s = sb.toString();  
        if (s.endsWith("&")) {  
            s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");  
        }  
        return s;  
    }
    
    
    /**
     * 将Map<String,Object>中的对应的空值转换为默认值或者空字符串
     * @see Object只能为单个的字符串String类型
     */
    @SuppressWarnings("rawtypes")
    private static <T> void transNullToEmptyString(Map<String,Object> targetMap) {
        if (targetMap.isEmpty()) {
            return;
        }else{
            //方式1
            for (Map.Entry entry : targetMap.entrySet()) {
                String key = (String) entry.getKey();
                if (null==key ) {
                    continue;
                }
                Object value = entry.getValue();
                if(null==value){
                    value="";
                    targetMap.put(key, value);
                }
            }
            
            //方式2
            /*Iterator entries = targetMap.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry)entries.next();
                String key = (String) entry.getKey();
                if (null==key ) {
                    continue;
                }
                Object value = entry.getValue();
                if(null==value){
                    value="";
                }
                targetMap.put(key, value);
            }*/
            
        }
    }
    
    /**
     * 将List<T>中的对应的空值转换为默认值或者空字符串
     * @see transNullToEmptyString(Object target)
     */
    public static <T> void transNullToEmptyString(List<T> targetList) {
        if (targetList == null || targetList.size()==0) {
            return;
        }else{
            for (T t : targetList) {
                transNullToEmptyString(t);
            }
        }
    }
    
    /**
     * 将实体中的String类型的null值转换为空字符串""
     * Long和Integer类型的null值转换为"-1"
     */
    public static void transNullToEmptyString(Object target) {
        if (target == null) {
            return;
        }
        PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance()
                .getPropertyUtils();
        PropertyDescriptor[] descriptors = propertyUtils
                .getPropertyDescriptors(target);
        try {
            if (descriptors != null) {
                for (PropertyDescriptor desc : descriptors) {
                    //使用前，用来判断一个类Class1和另一个类Class2是否相同或是另一个类的子类或接口
                    if (String.class.isAssignableFrom(desc.getPropertyType())) {
                        Method readMethod = desc.getReadMethod();
                        Object value = readMethod.invoke(target);
                        if (null==value) {
                            Method method = desc.getWriteMethod();
                            method.invoke(target, "");
                        }
                    }else if (Integer.class.isAssignableFrom(desc.getPropertyType())
                            ) {
                        Method readMethod = desc.getReadMethod();
                        Object value = readMethod.invoke(target);
                        if (null==value) {
                            Method method = desc.getWriteMethod();
                            method.invoke(target, "-1");
                        }
                    }else if (Long.class.isAssignableFrom(desc.getPropertyType())) {
                        Method readMethod = desc.getReadMethod();
                        Object value = readMethod.invoke(target);
                        if (null==value) {
                            Method method = desc.getWriteMethod();
                            method.invoke(target, -1L);
                        }
                    }else if (Double.class.isAssignableFrom(desc.getPropertyType())) {
                        Method readMethod = desc.getReadMethod();
                        Object value = readMethod.invoke(target);
                        if (null==value) {
                            Method method = desc.getWriteMethod();
                            method.invoke(target, -1D);
                        }
                    }else if (Float.class.isAssignableFrom(desc.getPropertyType())) {
                        Method readMethod = desc.getReadMethod();
                        Object value = readMethod.invoke(target);
                        if (null==value) {
                            Method method = desc.getWriteMethod();
                            method.invoke(target, -1F);
                        }
                    }else if (Timestamp.class.isAssignableFrom(desc.getPropertyType())) {
                        Method readMethod = desc.getReadMethod();
                        Object value = readMethod.invoke(target);
                        if (null==value) {
                            Method method = desc.getWriteMethod();
                            Date defaultDate = new SimpleDateFormat(TIMESTAMP_PATTERN).parse(DEFAULT_TIMESTAMP);
                            method.invoke(target, defaultDate);
                        }
                    }else if (Date.class.isAssignableFrom(desc.getPropertyType())) {
                        Method readMethod = desc.getReadMethod();
                        Object value = readMethod.invoke(target);
                        if (null==value) {
                            Method method = desc.getWriteMethod();
                            Date defaultDate = new SimpleDateFormat(DATE_PATTERN).parse(DEFAULT_DATE);
                            method.invoke(target, defaultDate);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("MyWebUtils.transNullToEmptyString exception", ex);
        }
    }
    
    /**
     * 将布尔值转换为字符串0或者1
     */
    public static String asString( boolean b ) {
        return b ? BOOLEAN_TRUE_IN_STR 
                : BOOLEAN_FALSE_IN_STR;
    }
    
    public static String asString( String obj ) {
        if(obj.indexOf('.')>0){
            obj=obj.substring(0, obj.indexOf('.'));
        }
        return obj;
    }
    
    public static String asString( float value ) {
//        NumberFormat numberFormat = NumberFormat.getInstance();
//        numberFormat.setMaximumFractionDigits( 1 );
        return String.valueOf( value );
    }
    
    /**
     * 是否ajax请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("x-requested-with") != null
                && request.getHeader("x-requested-with").equalsIgnoreCase(
                        "XMLHttpRequest");
    }
    
    /** 
     * 将Map<String, Object>对象中的属性值封装到request对象中(好像无用)
     */
    @Deprecated
    public static void parseMapToRequest(Map<String, Object> paramMap,HttpServletRequest request){
        WebUtils.exposeRequestAttributes(request, paramMap);
    }
    
    /** 
     * 将request对象中的值封装到相应的List<T>对象中 
     */ 
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> List<T> getListTFromRequest(Class<T> clazz, HttpServletRequest request,
            int rows, String paramPrefix) throws Exception {
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] propertyDescriptor = propertyUtilsBean.getPropertyDescriptors(clazz);
        Map<String, Stack<?>> map = new HashMap<String, Stack<?>>();
        List<T> list = new ArrayList<T>();
        String fieldName = "";
        for (int i = 0; i < propertyDescriptor.length; i++) {
            //字段属性名称
            fieldName = propertyDescriptor[i].getName();
            Object[] values = request.getParameterValues(paramPrefix + fieldName);
            if (values == null) {
                continue;
            }
            Stack stack = new Stack();
            for (int j = 0; j < values.length; j++) {
                stack.push(values[j]);
            }
            map.put(fieldName, stack);
        }
        try {
            for (int i = 0; i < rows; i++) {
                //Objcet obj = clazz.newInstance();
                T obj = clazz.newInstance();
                for (int m = 0; m < propertyDescriptor.length; m++) {
                    Stack stack = (Stack) map.get(propertyDescriptor[m].getName());
                    if (stack == null || stack.size() == 0) {
                        continue;
                    }

                    BeanUtils.setProperty(obj, propertyDescriptor[m].getName(), stack.pop());
                }
                list.add(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException("parse Exception :" + e);
        }
        
        //逆序集合
        Collections.reverse(list);
        return list;
    }

    
    /** 
     * 将request对象中的值封装到相应的pojo对象中 
     */ 
    public static void getObjectFromRequest(Object obj,
            HttpServletRequest request) throws Exception {
        // 获得对象类型
        Class<?> cla = obj.getClass();
        // 获得该类型中的所有属性
        Field field[] = cla.getDeclaredFields();
        // 遍历属性列表
        for (int i = 0; i < field.length; i++) {
            // 禁用访问控制检查
            field[i].setAccessible(true);
            // 获得属性类型
            Class<?> fieldType = field[i].getType();
            // 获得属性值
            String attr = request.getParameter(field[i].getName());
            if (attr == null) {
                // 如果属性值为null则不做任何处理，直接进入下一轮循环
                continue;
            }
            /**
             * 根据对象中属性类型的不同，将request对象中的字符串转换为相应的属性
             */
            if (fieldType == String.class) {
                field[i].set(obj, attr);
            } else if (fieldType == int.class) {
                // 当转换失败时，设置0
                field[i].set(obj, Integer.parseInt(request
                        .getParameter(field[i].getName())));
            } else if (fieldType == Date.class) {
                // 当转换失败时，设置为null
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .parse(request.getParameter(field[i].getName()));
                field[i].set(obj, date);
            }
        }
    }
    
    /**
     * 方式1：将request对象中的值封装到相应的Map<String, Object>对象中 
     */
    public static Map<String, Object> getMapFromRequest1(HttpServletRequest request) throws Exception{
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Enumeration<?> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parameterName = enumeration.nextElement().toString();
            resultMap.put(parameterName, request.getParameter(parameterName).trim());
        }
        return resultMap;
    }
    
    /**
     * 方式2：将request对象中的值封装到相应的Map<String, Object>对象中 
     */
    @SuppressWarnings("unchecked")
    public static Map<String ,Object> getMapFromRequest2(HttpServletRequest request) throws Exception{
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String ,Object> resultMap = new HashMap<String ,Object>();
        if (requestMap != null) {
            for (String key : requestMap.keySet()) {
                String[] values = requestMap.get(key);
                resultMap.put(key, values.length == 1 ? values[0].trim() : values);
            }
        }
        return resultMap;
    }
    
    /**
     *  删除文件:filePath为文件的全路径
     */
    public static boolean deleteFile(String filePath) throws Exception{
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     *  将UUID拼接上真实文件名后返回
     */
    public static String makeUUIDFileName(String realFileName,String nameSeparator) {
        if(StringUtils.isBlank(nameSeparator)){
            nameSeparator="_";
        }
        return UUID.randomUUID().toString() + nameSeparator + realFileName;
    }

    /**
     *  在upload总目录下，创建2层子目录，用于存上传的文件,分散总目录压力,子目录级数2级
     *  返回值中包含了传入的原来的文件名fileName
     */
    public static String makeSubFilePath(String filePath, String fileName) throws Exception{
        // 获取hashCode整型值
        int code = fileName.hashCode();
        // 第一级子目录
        int dir1 = code & 0xF;// 12
        // 第二级子目录
        int dir2 = (code >> 1) & 0xF;// 6
        // 创建这些子目录
        File file = new File(filePath + "/" + dir1 + "/" + dir2);
        // 如果不存在该子目录
        if (!file.exists()) {
            // 连续创建2个子目录
            file.mkdirs();
        }
        // 将创建后的子目录返回
        return file.getPath()+ "/"+fileName;
    }
    
    /**
     * 获取原始的request请求的ip地址信息（只适用自动获取的情况,不一定准确）
     */
    @Deprecated
    public static String getOriginIP(HttpServletRequest request) throws Exception{
        // 获取用户的IP地址
        String clientIp = request.getHeader("x-forwarded-for");
        if ((clientIp == null) || (clientIp.length() == 0)
                || ("unknown".equalsIgnoreCase(clientIp))) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if ((clientIp == null) || (clientIp.length() == 0)
                || ("unknown".equalsIgnoreCase(clientIp))) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((clientIp == null) || (clientIp.length() == 0)
                || ("unknown".equalsIgnoreCase(clientIp))) {
            clientIp = request.getRemoteAddr();
        }
        return formatIp(clientIp);
    }
    
    
    /**
     * 格式化并只取第一个ip，校验规则简单，只适用自动获取的情况.
     */
    public static String formatIp(String ip) throws Exception{
        if (ip == null) {
            return ip;
        }
        String newIp = ip.trim();
        if (!newIp.matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
            String splitStr = "";
            if (ip.contains(",")) {
                splitStr = ",";
            } else if (ip.contains("/")) {
                splitStr = "/";
            } else if (ip.contains("\\")) {
                splitStr = "\\\\";
            }
            if (splitStr.trim().length() > 0) {
                String[] ipArray = ip.split(splitStr);
                for (int i = 0; i < ipArray.length; i++) {
                    if (ipArray[i] != null
                            && ipArray[i].trim().matches(
                                    "(\\d{1,3}\\.){3}\\d{1,3}")) {
                        newIp = ipArray[i];
                        break;
                    }
                }
            }
        }
        return newIp;
    }

    
}
