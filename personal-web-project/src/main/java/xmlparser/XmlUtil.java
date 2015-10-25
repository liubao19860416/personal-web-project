package xmlparser;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取xml文件工具类<br>
 */
public class XmlUtil {
    
    private static Logger LOGGER = LoggerFactory.getLogger(XmlUtil.class);

    private XmlUtil() {
    }

    /**
     * 品牌document
     */
    private static Document brandDocument;

    /**
     * 用来读取brandDocument
     */
    public static Document getBrandDocument() {
        // 如果document存在直接返回
        if (brandDocument != null)
            return brandDocument;
        // 如果不存在就创建对象
        try {
            SAXReader reader = new SAXReader();
            brandDocument = reader.read(XmlUtil.class.getClassLoader()
                    .getResourceAsStream("conf/xml/brand.xml"));
            return brandDocument;
        } catch (DocumentException e) {
            LOGGER.error("----读取品牌xml出错----", e);
        }
        return null;
    }

}
