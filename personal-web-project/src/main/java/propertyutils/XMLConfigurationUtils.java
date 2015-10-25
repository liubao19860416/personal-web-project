package propertyutils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 使用commons-configuration的jar包，对XML格式的文件进行读取操作 OK
 * 比较有用！！！
 * 
 * @author Liubao
 * @2014年11月18日
 * 
 */
public class XMLConfigurationUtils {
    
 private static final Log logger = LogFactory.getLog(XMLConfigurationUtils.class);   
    
    private static XMLConfiguration config;
    //默认的文件名，放在classpath目录下
    private static String filePath="config.xml";;
    
    static{
        try {
            config = new XMLConfiguration(filePath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
            logger.error("默认初始化加载XML文件异常!!!" + filePath, e);
        }
    }
    
    /**
     * 获取当前Configuration对象，可以通过它，执行更多类型的get方法，如getDouble(key)等！
     * @return
     */
    public static Configuration getConfiguration() {
        return config;
    }
    
    
    /**
     * 根据文件名，或者文件对象，初始化指定Properties文件
     * 
     * 注意路径默认指向的是classpath的根目录     
     * 
     */
    // 初始化方法一
    public static void initPropertiesConfiguration(String filePath) {
        try {
            config = new XMLConfiguration(filePath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
            logger.error("初始化加载XML文件异常!!!" + filePath, e);
        }
    }

    // 初始化方法二
    public static void initPropertiesConfiguration(File file) {
        try {
            config = new XMLConfiguration(file);
        } catch (ConfigurationException e) {
            e.printStackTrace();
            logger.error("初始化加载XML文件异常!!!" + file.getAbsolutePath(), e);
        }
    }
    
    /**
     * 对于单独元素的话，可以直接通过标签名lableName获取值
     */
    public static String getStringByLableName(String lableName) {
        return config.getString(lableName);
    }
    
    /**
     * 对于单独元素的话，可以直接通过标签名lableName获取值
     */
    public static int getIntegerByLableName(String lableName) {
        return config.getInt(lableName);
    }
    
    /**
     * 根据lableName值，对多个的同名标签String类型value值进行读取；
     * 同时，如果只有一个标签的话，其值是多个用逗号分隔的字符串，也可以该字符串转换为list或者数组
     * 两者同时存在的话，都会获取到。
     */
    @SuppressWarnings("unchecked")
    public static List<String> getListByLableName(String lableName) {
        //System.out.println(Arrays.toString(config.getList(lableName).toArray()));  
        return config.getList(lableName);
    }
    
    /**
     * 根据lableName值，对多个的同名标签String类型value值进行读取；
     * 同时，如果只有一个标签的话，其值是多个用逗号分隔的字符串，也可以该字符串转换为list或者数组
     * 两者同时存在的话，都会获取到。
     */
    public static String[] getArrayByLableName(String lableName) {
        //System.out.println(Arrays.toString(keys));  
        return config.getStringArray(lableName);
    }
    
    /**
     * 根据lableName值，对多个的同名标签String类型value值进行读取；使用自定义分隔符
     * delimiter：自定义的分隔符
     */
    public static String[] getArrayByLableNameUseDelimiter(String lableName,char delimiter) {
        //设置新的分隔符
        AbstractConfiguration.setDefaultListDelimiter(delimiter);  
        //重新初始化加载properties文件
        initPropertiesConfiguration(filePath);
        System.out.println(Arrays.toString(config.getStringArray(lableName)));  
        return config.getStringArray(lableName);
    }
    
    /**
     * 根据lableName值，对多个的同名标签String类型value值进行读取；使用自定义分隔符
     * delimiter：自定义的分隔符
     */
    @SuppressWarnings("unchecked")
    public static List<String> getListByLableNameUseDelimiter(String lableName,char delimiter) {
        //设置新的分隔符
        AbstractConfiguration.setDefaultListDelimiter(delimiter);  
        //重新初始化加载properties文件
        initPropertiesConfiguration(filePath);
        System.out.println(Arrays.toString(config.getList(lableName).toArray()));  
        return config.getList(lableName);
    }
    
    /**
     * 根据lableName值和其属性attribute，获取其对应的该属性的值
     * 
     * 对于标签元素的属性，可以通过 标签名[@属性名] 这样的方式获取
     * 
     * 存在多个数值的时候，取第一个数值；
     * 
     * 多级标签，使用"."进行获取该标签值，注意使用角标的时候，从0开始；
     */
    public static String getAttributeByLableName(String lableName,String attribute) {
        return config.getString(lableName+"[@"+attribute+"]");
    }
    
    
    
    
    //简易测试
    public static void main(String[] args) {
//        String stringByKey = XMLConfigurationUtils.getStringByLableName("boy");
//        System.out.println(stringByKey);
//        
//        stringByKey = XMLConfigurationUtils.getStringByLableName("student");
//        System.out.println(stringByKey);
//        
//        stringByKey = XMLConfigurationUtils.getStringByLableName("title");
//        System.out.println(stringByKey);
//        
//        System.out.println("====================");
//        List<String> list = XMLConfigurationUtils.getListByLableName("title");
//        for (String string : list) {
//            System.out.println(string);
//        }
//        System.out.println("====================");
//        
//        String[] arrayByLableName = XMLConfigurationUtils.getArrayByLableNameUseDelimiter("student.name",'-');
//        for (String string : arrayByLableName) {
//            System.out.println(string);
//        }
//        System.out.println("====================");
        
        String attr = XMLConfigurationUtils.getAttributeByLableName("ball","size");
        System.out.println(attr);
        
        // 对于嵌套的标签的话，想获得某一项的话可以通过 标签名(索引名) 这样方式获取
        attr = XMLConfigurationUtils.getAttributeByLableName("student.name","go");
        System.out.println(attr);
        
        attr = XMLConfigurationUtils.getAttributeByLableName("student(1).name","go");
        System.out.println(attr);
        
        attr = XMLConfigurationUtils.getAttributeByLableName("student(1).name(1)","go");
        System.out.println(attr);
        
        attr = XMLConfigurationUtils.getStringByLableName("student(1).name(1)");
        System.out.println(attr);

    }
    

}
