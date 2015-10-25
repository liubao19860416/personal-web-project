package propertyutils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 简单的读取和写入配置文件操作工具类 OK
 * 
 * 对于key值，区分大小写！！！
 * 
 * 
 */
public class PropertiesHelper {
    private static Log logger = LogFactory.getLog(PropertiesHelper.class);

    private static  String project_root="" ;
    //默认的文件名，放在classpath目录下
    private static  String filePath="/messages.properties";
    private static  String absoulateFilePath="";
    
    //通过static代码快加载配置文件信息
    static{
        try {
            // 构造时获取到项目的物理根目录
            project_root = Thread.currentThread().getClass().getResource("/").getPath().replace("file:/", "");
            if(project_root.indexOf("/")==0){
                project_root=project_root.substring(1,project_root.length());
            }
            //project_root = this.getClass().getResource("/").toString().replace("file:/", "");
            if (project_root.indexOf("/WEB-INF") != -1) {
                project_root = project_root.substring(0,project_root.indexOf("/WEB-INF"));
            } else if (project_root.indexOf("/target/classes") != -1) {
                project_root = project_root.substring(0,project_root.indexOf("/target/classes"));
            }
            project_root+="/src/main/resources/";
            logger.info("项目初始化获取的Properties文件根路径为："+project_root);
        } catch (Exception e) {
            logger.error("初始化加载Properties文件失败！！！"+absoulateFilePath,e);
        }
        
    }

    /**
     * 构造方法初始化指定需要读取的文件路径名称（静态变量需要构造方法初始化或者静态代码块初始化）
     * /personal-web-project/src/main/resources/messages.properties
     * @param filePath
     */
    public PropertiesHelper(String filePath) {
        //初始化
        initPropertiesFilePth(filePath);
    }
    
    /**
     * 初始化配置文件路径的方法
     */
    @SuppressWarnings("static-access")
    private void initPropertiesFilePth(String filePath) {
        if(StringUtils.isNotBlank(filePath)){
            if(filePath.indexOf("/")==-1||filePath.indexOf("/")!=0){
                filePath="/"+filePath;
            }
            this.filePath=filePath;
        }
        if (StringUtils.isNotBlank(filePath)) {
            absoulateFilePath=project_root+filePath;
            logger.info("项目初始化获取的Properties文件全路径为："+absoulateFilePath);
        }
    }
    
    
    
    //===================================具体方法列表========================================

    
    
    
    /**
     * 读取属性对应的值,当存在多个同key的value值时，取最后一个key对应的value值
     */
    public String getPropertiesByKey(String key) {
        Properties prop = new Properties();
        InputStream fis = null;
        try {
            fis = new FileInputStream(absoulateFilePath);
            prop.load(fis);
            return prop.getProperty(key);
        } catch (Exception e) {
            logger.error("获取Properties文件属性key:"+key+"失败！！！",e);
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                logger.error("流关闭异常！！！",e);
            }finally{
                fis=null;
            }
        }
        //不存在返回空串
        return "";
    }

    /**
     * 设置对应的属性值；
     * 当文件中存在多个同名key的时候，会在设置后去除重复的key，只取最后一个有效的key值；
     * 当key已经存在的时候，直接进行覆盖更新！！！
     */
    public void setProperties(String key, String value) throws Exception {
        Properties prop = new Properties();
        FileOutputStream outputFile = null;
        InputStream fis = null;
        try {
            // 输入流和输出流要分开处理， 放一起会造成写入时覆盖以前的属性
            fis = new FileInputStream(absoulateFilePath);
            // 先载入已经有的属性文件
            prop.load(fis);
            // 追加新的属性
            prop.setProperty(key, value);
            // 写入属性,采用追加写入的方式！！！这里已经读取了
            outputFile = new FileOutputStream(absoulateFilePath,false);
            prop.store(outputFile, "add record as("+key+"==>>"+value+") by Liubao...");
            outputFile.flush();
        } catch (Exception e) {
            logger.error("添加记录("+key+"==>>"+value+")信息失败！！！",e);
            throw e;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (outputFile != null) {
                    outputFile.close();
                }
            } catch (Exception e) {
                logger.error("流关闭异常！！！", e);
            } finally {
                fis = null;
                outputFile = null;
            }
            logger.info("添加记录("+key+"==>>"+value+")信息成功！！！");
        }
    }
    
    //====================================更新操作方法========================================
    
    /**
     * 修改属性文件中key的值为value；
     * 当文件中存在多个同名key的时候，会在更新后去除重复的key，只取最后一个有效的key值；
     */
    @Deprecated
    public void updateProperties(String key, String value) {
        //首先读取，是否存在，不存在直接返回
        String byKey = getPropertiesByKey(key);
        if (StringUtils.isBlank(byKey)) {
            logger.info("更新记录（"+key+"的值不存在，请先调用添加属性值方法！！！");
            return;
        }
        
        // 保存属性到指定文件中
        Properties pop = new Properties();
        FileOutputStream out = null;
        // 从文件properties中读取key-value信息
        FileInputStream in = null;
        try {
            //读取当前配置文件
            in = new FileInputStream(absoulateFilePath);
            pop.load(in);
            
            //执行更新操作
            out = new FileOutputStream(absoulateFilePath, false);
            pop.put(key, value);
            pop.store(out, "update properties file,"+key+":FROM["+byKey+"],TO["+value+"]");
        } catch (Exception e) {
            logger.error("更新记录("+key+":FROM["+byKey+"],TO["+value+"])信息失败！！！");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("流关闭失败！！！");
            }finally{
                out=null;
                in=null;
            }
            logger.info("更新记录("+key+":FROM["+byKey+"],TO["+value+"])信息成功！！！");
        }
    }
    
    
    public static void main(String[] args) throws Exception {
        PropertiesHelper helper = new PropertiesHelper(filePath);
        //测试获取属性值方法
        String result = helper.getPropertiesByKey("username");
        System.out.println(result);
        
        //测试添加属性值方法
        //helper.setProperties("username", "LIUBAO");
        
        //测试更新属性值方法
        //helper.updateProperties("con1", "new value");
        
    }
    
}
