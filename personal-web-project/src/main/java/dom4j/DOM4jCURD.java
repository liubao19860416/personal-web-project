package dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class DOM4jCURD {

    // ��Ӹ���Ԫ�� book
    @Test
    public void demo7() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore3.xml"));

        Element bookstore = document.getRootElement();

        // ��ʽһ
        // // ���� book Ԫ��
        // Element book = DocumentHelper.createElement("book");
        // book.addAttribute("category", "CHILDREN");
        // // ���� titleԪ��
        // Element title = DocumentHelper.createElement("title");
        // title.addAttribute("lang", "zh");
        // title.setText("�췽ҹ̷");
        // // ���� author
        // Element author = DocumentHelper.createElement("author");
        // author.setText("����");
        //
        // book.add(title);
        // book.add(author);
        // bookstore.add(book);

        // ��ʽ��
        String xmlpart = "<book category=\"CHILDREN\"><title lang=\"zh\">�췽ҹ̷</title><author>����</author></book>";
        Document bookDocument = DocumentHelper.parseText(xmlpart);
        bookstore.add(bookDocument.getRootElement());

        // ��дXML Ĭ����� ���뼯 UTF-8
        XMLWriter writer = new XMLWriter(new FileOutputStream("bookstore3.xml"));
        writer.write(document);
        writer.close();
    }

    // ɾ�� bid ����
    @Test
    public void demo6() throws Exception {
        // DOM ��д ����xml�ĵ�
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore3.xml"));

        List<Element> books = document.getRootElement().elements("book");
        for (Element book : books) {
            Attribute bid = book.attribute("bid");
            book.remove(bid);
        }

        // ��дXML Ĭ����� ���뼯 UTF-8
        XMLWriter writer = new XMLWriter(new FileOutputStream("bookstore3.xml"));
        writer.write(document);
        writer.close();
    }

    // ɾ�� number �ӽڵ�
    @Test
    public void demo5() throws Exception {
        // DOM ��д ����xml�ĵ�
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore3.xml"));

        List<Element> books = document.getRootElement().elements("book");
        for (Element book : books) {
            Element number = book.element("number");
            number.getParent().remove(number);
        }

        // ��дXML Ĭ����� ���뼯 UTF-8
        XMLWriter writer = new XMLWriter(new FileOutputStream("bookstore3.xml"));
        writer.write(document);
        writer.close();
    }

    // Ϊÿ��ͼ�� ��� bid ���� ֵΪ1000
    @Test
    public void demo4() throws Exception {
        // �޸� ���� ��д
        // DOM ��д ����xml�ĵ�
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore3.xml"));

        List<Element> books = document.getRootElement().elements("book");
        for (Element book : books) {
            book.addAttribute("bid", "1000");
        }

        // ��дXML Ĭ����� ���뼯 UTF-8
        XMLWriter writer = new XMLWriter(new FileOutputStream("bookstore3.xml"));
        writer.write(document);
        writer.close();
    }

    // Ϊÿ��ͼ�� ��� ��Ԫ�� number ֵΪ100
    @Test
    public void demo3() throws Exception {
        // �޸� ���� ��д
        // DOM ��д ����xml�ĵ�
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore3.xml"));

        List<Element> books = document.getRootElement().elements("book");
        for (Element book : books) {
            // ���Ԫ�� ǰ ���ȴ���Ԫ��
            Element number = DocumentHelper.createElement("number");
            number.setText("100");

            // appendChild
            // book.add(number);

            // ���number ��author ���� (�����λ��)
            List<Element> children = book.elements();
            children.add(2, number);

        }

        // ��дXML Ĭ����� ���뼯 UTF-8
        OutputFormat format = OutputFormat.createPrettyPrint();// ָ��XML����
        format.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(
                new FileOutputStream("bookstore3.xml"), "utf-8"), format);
        writer.write(document);
        writer.close();

    }

    @Test
    public void demo2() throws Exception {
        // �޸� ���� ��д
        // DOM ��д ����xml�ĵ�
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore3.xml"));

        // �޸� ����Ϊ Children ͼ�� ����Ϊ ��ʿ��
        List<Element> books = document.getRootElement().elements("book");
        for (Element book : books) {
            if ("CHILDREN".equals(book.attributeValue("category"))) {
                book.element("author").setText("��ʿ��");
            }
        }

        // ��дXML Ĭ����� ���뼯 UTF-8
        // XMLWriter writer = new XMLWriter(new
        // FileOutputStream("bookstore3.xml"));
        // writer.write(document);
        // writer.close();

        OutputFormat format = OutputFormat.createPrettyPrint();// ָ��XML����
        format.setEncoding("utf-8");
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(
                new FileOutputStream("bookstore3.xml"), "utf-8"), format);
        writer.write(document);
        writer.close();
    }

    // �޸Ļ�д
    @Test
    public void demo1() throws Exception {
        // DOM ��д ����xml�ĵ�
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("bookstore3.xml"));
        // �ڴ����޸� DOMģ�����
        // ���� ÿ���� �۸�+100
        Element root = document.getRootElement();
        List<Element> books = root.elements("book");
        for (Element book : books) {
            String value = book.elementText("price");
            value = String.valueOf(Double.parseDouble(value) + 100);
            // value = Double.parseDouble(value)+100+"";
            book.element("price").setText(value);
        }
        // ��дXML
        XMLWriter writer = new XMLWriter(new FileOutputStream("bookstore3.xml"));
        writer.write(document);
        writer.close();
    }
}
