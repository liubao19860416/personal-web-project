package xmlparser;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * xml格式文件操作工具类
 * 
 * @author Liubao
 * @2014年11月18日
 *
 */
public class XMLHelperUtils {
    
    /**
     * 将对象转换为json,使用它的转换，只与对象的属性有关，而不需要set和get方法！！！
     */
    public static String writeObject2JSON(Object obj) {
        XStream xStream = new XStream(new JsonHierarchicalStreamDriver());
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias(obj.getClass().getSimpleName().toLowerCase(), obj.getClass());
        return xStream.toXML(obj);
        }
    
    /**
     * 将对象转为XML
     */
    public static String simpleObject2XML(Object obj) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias(obj.getClass().getSimpleName(), obj.getClass());
        String xml = xStream.toXML(obj);
        return xml;
    }

    /**
     *  将XML字符串转为对象
     */
    public static Object simpleXML2Object(String xml, Object obj) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias(obj.getClass().getSimpleName(), obj.getClass());
        Object reobj = xStream.fromXML(xml);
        return reobj;
    }
}
