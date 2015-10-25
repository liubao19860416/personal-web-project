package xmlparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * DOM 解析xml文件工具类
 * @author Liubao
 * @2014年11月30日
 *
 */
public class KeyXMLTool {

    private static String fileName = "config.xml";

    // 得到WEB-INF的绝对路径
    private static String web_inf_Path = new File(KeyXMLTool.class.getResource("/").getPath()).getParent();

    private static String filePath = web_inf_Path + "\\" + fileName;

    private static DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();

    public static String getUrl() {
        return filePath;
    }

    public static String getFileUrl(String fileName) {
        return web_inf_Path + "\\" + fileName;
    }

    public static String getRootWebUrl() {
        return web_inf_Path;
    }

    /**
     * 根据xml文件名，获取Document对象
     */
    private static Document getDocumentByFileName(final String fileName) {
        Document doc = null;
        try {
            DocumentBuilder dombuilder = domFactory.newDocumentBuilder();
            InputStream in = new FileInputStream(fileName);
            // InputStream in=ClassLoader.getSystemResourceAsStream(fileName);
            // InputStream in = KeyXMLTool.class.getResourceAsStream("/Key.xml");
            doc = dombuilder.parse(in);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doc;
    }
    
    public static String getPINKey() {
        String pinKey = null;
        Document doc = getDocumentByFileName(filePath);
        NodeList pinKeyNodeList = doc.getElementsByTagName("pinkey");
        Node pinKeyNode = pinKeyNodeList.item(0);
        pinKey = pinKeyNode.getFirstChild().getNodeValue();
        return pinKey;
    }

    /**
     * 设置标签为pinKey的元素对应的值
     */
    public static synchronized void setPINKEY(final String pinKey) {
        Document doc = getDocumentByFileName(filePath);
        NodeList pinKeyNodeList = doc.getElementsByTagName("pinkey");
        Node pinKeyNode = pinKeyNodeList.item(0);
        pinKeyNode.getFirstChild().setNodeValue(pinKey);
        doc2XmlFile(doc, filePath);
    }

    /**
     * 将document中的内容写入文件中
     */
    public static boolean doc2XmlFile(Document document, String filename) {
        boolean flag = true;
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            // 编码
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
        } catch (Exception ex) {
            flag = false;
            ex.printStackTrace();
        }
        return flag;
    }
    
    
    public static void main(String[] args) {

    }
    

}
