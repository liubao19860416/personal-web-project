package dom4j;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XPathTest {
    @Test
    public void demo3() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore3.xml"));

        String expr = "//book[starts-with(title,'Harry')]";

        List<Element> books = document.selectNodes(expr);

        for (Element book : books) {
            System.out.println(book.elementText("author"));
        }
    }

    @Test
    public void demo2() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore3.xml"));

        String expr = "//book[title='�췽ҹ̷']";

        List<Element> books = document.selectNodes(expr);

        for (Element book : books) {
            System.out.println(book.elementText("author"));
        }
    }

    @Test
    public void demo1() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore3.xml"));

        String expr = "//title[@lang='en']";

        List<Element> titles = document.selectNodes(expr);

        for (Element title : titles) {
            System.out.println(title.getText());
        }
    }
}
