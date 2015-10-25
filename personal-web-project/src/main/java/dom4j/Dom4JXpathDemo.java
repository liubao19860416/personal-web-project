package dom4j;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.Test;

public class Dom4JXpathDemo {
    @Test
    public void test1() {
        try {
            Document document = Dom4JUtil.getDocument();
            // �Ӹ�Ԫ�ؿ�ʼ
            Element root = document.getRootElement();
            List<Element> booksElements = root.elements("book");
            Element authorElement = booksElements.get(1).element("author");
            System.out.println(authorElement.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test11() {
        try {
            Document document = Dom4JUtil.getDocument();
            Node node = document.selectSingleNode("//book[2]/author");
            System.out.println(node.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        try {
            Document document = Dom4JUtil.getDocument();
            // �Ӹ�Ԫ�ؿ�ʼ
            Element root = document.getRootElement();
            List<Element> booksElements = root.elements("book");
            Element sencodBook = booksElements.get(1);
            System.out.println(sencodBook.attributeValue("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test21() {
        try {
            Document document = Dom4JUtil.getDocument();
            Node node = document.selectSingleNode("//book[2]");
            System.out.println(node.valueOf("@id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
