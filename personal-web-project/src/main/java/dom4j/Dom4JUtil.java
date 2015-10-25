package dom4j;

import java.io.FileOutputStream;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4JUtil {

    // users.xml����ʵ·��
    private static String DB_FILEPATH;
    static {
        ClassLoader cl = Dom4JUtil.class.getClassLoader();
        URL url = cl.getResource("users.xml");
        DB_FILEPATH = url.getPath();
    }

    public static Document getDocument() throws Exception {
        SAXReader reader = new SAXReader();
        return reader.read(DB_FILEPATH);
    }

    public static void write2xml(Document document) throws Exception {
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileOutputStream(DB_FILEPATH),
                format);
        writer.write(document);
        writer.close();
    }
}
