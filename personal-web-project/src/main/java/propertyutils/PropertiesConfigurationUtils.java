package propertyutils;

import java.io.File;
import java.util.List;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 使用commons-configuration的jar包，对Properties格式的文件进行读取操作 OK
 * 
 * 比较有用！！！
 * 
 * @author Liubao
 * @2014年11月18日
 * 
 */
public class PropertiesConfigurationUtils {
    
    private static final Log logger = LogFactory.getLog(PropertiesConfigurationUtils.class);   
    
    private static Configuration config;
    //默认的文件名，放在classpath目录下
    private static String filePath="messages.properties";
    
    static{
        try {
            config = new PropertiesConfiguration(filePath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
            logger.error("默认初始化加载Properties文件异常!!!" + filePath, e);
        }
    }
    
    /**
     * 获取当前Configuration对象，可以通过它，执行更多类型的get方法，如getDouble(key)等！
     * @return
     */
    public static Configuration getConfiguration() {
        return config;
    }
    
    
    //=============================初始化Properties方法==========================================
    
    /**
     * 根据文件名，或者文件对象，初始化指定Properties文件
     * 
     * 注意路径默认指向的是classpath的根目录     
     * 
     */
    // 初始化方法一
    public static void initPropertiesConfiguration(String filePath) {
        try {
            config = new PropertiesConfiguration(filePath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
            logger.error("初始化加载Properties文件异常!!!" + filePath, e);
        }
    }

    // 初始化方法二
    public static void initPropertiesConfiguration(File file) {
        try {
            config = new PropertiesConfiguration(file);
        } catch (ConfigurationException e) {
            e.printStackTrace();
            logger.error("初始化加载Properties文件异常!!!" + file.getAbsolutePath(), e);
        }
    }
    
    
    //================================查询方法列表=======================================
    
    /**
     * 根据key值，对单一的String类型value值进行读取
     */
    public static String getStringByKey(String key) {
        String result = config.getString(key);
        if(StringUtils.isBlank(result)){
            result=config.getString(key, "您还没有添加该属性值！！！");
        }
        return result;
    }
    
    /**
     * 根据key值，对单一的Integer类型value值进行读取
     */
    public static int getIntegerByKey(String key) {
        return config.getInt(key);
    }
    
    /**
     * 根据key值，对多个的String类型value值进行读取，默认的分隔符是逗号“，”
     */
    @SuppressWarnings("unchecked")
    public static List<String> getListByKey(String key) {
        //System.out.println(Arrays.toString(config.getList(key).toArray()));  
        return config.getList(key);
    }
    
    /**
     * 根据key值，对多个的String类型value值进行读取，默认的分隔符是逗号“，”
     */
    public static String[] getArrayByKey(String key) {
        //System.out.println(Arrays.toString(keys));  
        return config.getStringArray(key);
    }
    
    /**
     * 根据key值，对多个的String类型value值进行读取，自定义分隔符
     * delimiter：自定义的分隔符
     */
    public static String[] getArrayByKeyUseDelimiter(String key,char delimiter) {
        //设置新的分隔符
        AbstractConfiguration.setDefaultListDelimiter(delimiter);  
        //重新初始化加载properties文件
        //initPropertiesConfiguration(filePath);
        //System.out.println(Arrays.toString(config.getStringArray(key)));  
        return config.getStringArray(key);
    }
    
    /**
     * 根据key值，对多个的String类型value值进行读取，自定义分隔符
     * delimiter：自定义的分隔符
     */
    @SuppressWarnings("unchecked")
    public static List<String> getListByKeyUseDelimiter(String key,char delimiter) {
        //设置新的分隔符
        AbstractConfiguration.setDefaultListDelimiter(delimiter);  
        //重新初始化加载properties文件
        //initPropertiesConfiguration(filePath);
        //System.out.println(Arrays.toString(config.getList(key).toArray()));  
        return config.getList(key);
    }
    
    
    //================================添加方法列表=======================================
    /**
     * 添加属性的方法key-value格式,只是在内存config对象中更新，没有写到文件中去
     */
    @Deprecated
    public static void addPropertyByKey(String key,String value) {
        //initPropertiesConfiguration(filePath);
        config.addProperty(key, value);
    }
    
    //===============================更新方法列表=======================================
    /**
     * 更新属性的方法key-value格式，只是在内存config对象中更新，没有写到文件中去
     */
    @Deprecated
    public static void setPropertyByKey(String key,String value) {
        config.setProperty(key, value);
    }
    
    //=========================清除配置信息对象config中key的信息，相当于删除方法=====================================
    /**
     * 清除配置信息对象config中key的信息，只是清除在内存config对象,没有写到文件中去
     */
    @Deprecated
    public static void clearPropertyByKey(String key) {
        config.clearProperty(key);
    }
    
    /**
     * 清除配置信息对象config中所有的信息,只是清除在内存config对象,没有写到文件中去
     */
    @Deprecated
    public static void clearConfiguration() {
        config.clear();
    }
    
    
    //===================================简易测试main方法===============================================
    //简易测试
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        String[] arrays = PropertiesConfigurationUtils.getArrayByKeyUseDelimiter("con",'-');
        //输出数组字符串
        //System.out.println("arrays输出为："+Arrays.toString(arrays));
        
        List<String> list = PropertiesConfigurationUtils.getListByKeyUseDelimiter("con",'-');
        //System.out.println("list输出为："+Arrays.toString(list.toArray()));
        
        String key="application.title";
        //可以获取${application.name} ${application.version}格式的值
        String string = PropertiesConfigurationUtils.getStringByKey(key);
        //System.out.println(string);
        
        
        //============================无用=========================================
        
        //清除指定key信息
        PropertiesConfigurationUtils.clearPropertyByKey(key);
        string = PropertiesConfigurationUtils.getStringByKey(key);
        //System.out.println(string);
        
        //清除所有信息
        PropertiesConfigurationUtils.clearConfiguration();
        string = PropertiesConfigurationUtils.getStringByKey(key);
        //System.out.println(string);
        
        //添加和更新属性,只是在内存级别操作，并没有修改Properties文件
        //PropertiesConfigurationUtils.setPropertyByKey("username","LIUBAO1");
        //PropertiesConfigurationUtils.addPropertyByKey("username","LIUBAO2");
        string = PropertiesConfigurationUtils.getStringByKey("username");
        System.out.println(string);
    }
    
}
