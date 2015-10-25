package dom4j;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Dom4jTest {

    @Test
    public void demo3() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore2.xml"));

        Element root = document.getRootElement();
        List<Element> books = root.elements("book");

        for (Element book : books) {
            List<Attribute> attributes = book.attributes();
            for (Attribute attribute : attributes) {
                System.out.println(attribute.getName() + ":"
                        + attribute.getText() + "," + attribute.getValue());
            }
        }

    }

    @Test
    public void demo2() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore2.xml"));

        // ����ÿ���� �ı������children
        Element root = document.getRootElement();// ��ø�ڵ�

        List<Element> books = root.elements("book"); // �������book�ӽڵ�
        for (Element book : books) {
            // ��� book �������� attributeValue
            String categoryValue = book.attributeValue("category");
            if ("CHILDREN".equals(categoryValue)) {
                // ��ǰ book ����Ҫ�ҵ���
                Element title = book.element("title");
                System.out.println(title.getText()); // title.getFirstChild().getNodeValue()

                System.out.println(book.elementText("title"));
            }
        }
    }

    @Test
    public void demo1() throws DocumentException {
        // ʹ��DOM ����ʱ ---- ��һ�� ����xml�ĵ������Document����
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore2.xml"));

        Element bookstore = document.getRootElement();// ��ø�Ԫ��
        System.out.println(bookstore.getName());

        List<Element> list = bookstore.elements();
        System.out.println(list.size());
        for (Element e : list) {
            System.out.println(e.getName());
            System.out.println(e.attributeValue("category"));
            System.out.println(e.attribute("category").getValue());
        }
    }
}
