package base.controller.result;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 资源文件读取工具类
 */
public class ResourcesUtil implements Serializable {
    
    private static final Logger logger = LoggerFactory.getLogger(ResourcesUtil.class);

	private static final long serialVersionUID = -7657898714983901418L;

	private static final String DEFAULT_LANGUAGE = "zh";//系统语言环境，默认为中文zh
	private static final String DEFAULT_COUNTRY = "CN";//系统国家环境，默认为中国CN
	private static final String DEFAULT_PROPERTIES_FILENAME = "messages";//默认的资源文件名称
	
	//测试
    public static void main(String[] args) {
        System.out.println(getResourcesValue("messages", "application.title"));
        System.out.println(getResourcesValue("messages", "900"));
        System.out.println(getResourcesValue("messages", "907",new Object[]{100,200}));//操作成功100条，失败200条
        System.out.println(getResourcesValue("messages", "907",100,200));//操作成功100条，失败200条
        System.out.println(getResourcesValue("907",new Object[]{100,200}));//操作成功100条，失败200条
        //System.out.println(getResourcesValue("messages", "907",null));//操作成功{0}条，失败{1}条
        System.out.println(getResourceskeyList("messages"));
    }
	
	//获取本地语言环境
	private static Locale getLocale() {
		return new Locale(DEFAULT_LANGUAGE, DEFAULT_COUNTRY);
	}

	/**
	 * 资源文件名和key名字获取资源文件值,使用默认的语言,国家代码；
	 */
	private static String getProperties(String fileName, String key) {
		String value = "";
		if(fileName==null||fileName.length()==0||"".equals(fileName)){
		    fileName=DEFAULT_PROPERTIES_FILENAME;
		}
		try {
			Locale locale = getLocale();
			ResourceBundle rb = ResourceBundle.getBundle(fileName, locale);
			value = (String) rb.getObject(key);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取资源文件【"+fileName+"】异常。。。",e);
		}
		return value;
	}

	/**
	 * 通过key从资源文件读取内容
	 */
	public static String getResourcesValue(String fileName, String key) {
		return  getProperties(fileName,key);
	}
	public static String getResourcesValue(String key) {
	    return  getProperties(null,key);
	}
	
	 /**
     * 通过key从资源文件读取属性值，并格式化，将obj中的信息转换为一个字符串；
     * 这里使用的Object[]代替可变参数
     * 在properties中定义的格式为：907=操作成功{0}条，失败{1}条
     * Object[] objs和Object... objs是等价的
     */
    public static String getResourcesValue(String fileName, String patternKey, Object... objs) {
        String pattern = getResourcesValue(fileName, patternKey);
        String value = MessageFormat.format(pattern, objs);
        return value;
    }
    /**
     * Object[] objs和Object... objs是等价的
     */
    public static String getResourcesValue(String patternKey, Object[] objs) {
        String pattern = getResourcesValue(null, patternKey);
        String value = MessageFormat.format(pattern, objs);
        return value;
    }

	/**
     * 获取资源文件fileName的key的list集合
     */
	public static List<String> getResourceskeyList(String fileName) {
	    if(fileName==null||fileName.length()==0||"".equals(fileName)){
            fileName=DEFAULT_PROPERTIES_FILENAME;
        }
		Locale locale = getLocale();
		ResourceBundle rb = ResourceBundle.getBundle(fileName, locale);
		/*List<String> keyList =new ArrayList<String>() ;
		Set<String> keyset = rb.keySet();
		for (Iterator<String> it = keyset.iterator(); it.hasNext();) {
			String lkey = (String)it.next();
			keyList.add(lkey);
		}*/
		Set<String> keyset = rb.keySet()==null?new TreeSet<String>():rb.keySet();
		return new ArrayList<String>(keyset);
	}
	/**
     * 获取默认资源文件的key的list集合
     */
	public static List<String> getResourceskeyList() {
	    Locale locale = getLocale();
	    ResourceBundle rb = ResourceBundle.getBundle(DEFAULT_PROPERTIES_FILENAME, locale);
	    Set<String> keyset = rb.keySet()==null?new TreeSet<String>():rb.keySet();
	    return new ArrayList<String>(keyset);
	}

}
