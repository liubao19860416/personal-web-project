import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class xml_dom4jparse {

	// @Test
	public void test01() throws Exception {

		// ��ȡ�ڵ㣻

		// ������Ҫ��dom4j��jar����
		// ��ý���������
		SAXReader saxReader = new SAXReader();
		// ���document����
		Document document = saxReader.read("xml_Test_01.xml");
		// ��ȡ��Ԫ�أ�Ȼ���𼶻�ȡ����Ԫ�أ���������в�����ע�⣺ֻ���𼶻�ȡList���ͼ���Ԫ�أ�
		Element rootElement = document.getRootElement();
		List<Element> elements = rootElement.elements("book");
		// System.out.println(elements.size());
		for (Element element : elements) {
			System.out.println(element.getName() + ":" + element.getText()
					+ ":" + element.getNodeTypeName());
			System.out.println(element.getStringValue());
		}
	}

	@Test
	public void test02() throws Exception {

		// ��ȡ����ֵ��
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read("xml_Test_01.xml");

		Element rootElement = doc.getRootElement();
		List<Element> list = rootElement.elements("book");

		// list = rootElement.elements("name");
		// System.out.println(list.size());

		for (int i = 0; i < list.size(); i++) {
			// System.out.println(list.get(i).getName()+":"+list.get(i).getNodeTypeName()+":"+list.get(i).getText());
			System.out.println(list.get(i).attributeValue("id"));
		}

	}

	// @Test
	public void test03() throws Exception {
		// ��ӽڵ㣻<ISBN>
		SAXReader saxreader = new SAXReader();
		Document doc = saxreader.read("xml_Test_01.xml");

		Element rootElement = doc.getRootElement();
		// ʹ�ð����ۣ�����Ԫ�ض���
		// createElement.setAttributeValue("id", "10010");

		List<Element> bookList = rootElement.elements("book");

		for (int i = 0; i < bookList.size(); i++) {
			Element createElement = DocumentHelper.createElement("ISBN");
			createElement.setText("10011" + i);
			// createElement.addAttribute("ISBN", "10010");
			bookList.get(i).add(createElement);
		}

		writeBackDoc(doc);

	}

	private void writeBackDoc(Document doc) throws Exception {
		// ��ȡ�������õ����Ԫ�ظ�ʽformat��
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");// ָ����������ʽ��

		// FileWriterĬ�ϲ���ϵͳ���룬����ʹ��FileOutputStream
		// XMLWriter xmlwriter=new XMLWriter(new
		// FileWriter("xml_test_dom4j.xml"),format);
		XMLWriter xmlwriter = new XMLWriter(new FileOutputStream(
				"xml_test_dom4j.xml"), format);
		xmlwriter.write(doc);
		xmlwriter.close();
	}

	// @Test
	public void test04() throws Exception {

		// ɾ��������
		SAXReader saxreader = new SAXReader();
		Document doc = saxreader.read("xml_Test_01.xml");
		Element rootElement = doc.getRootElement();
		// ֻ���𼶻�ȡ���е�Ԫ�أ����ܿ缶��ȡ������ֻ���Ȼ�ȡbook���ٻ�ȡprice��
		List<Element> bookements = rootElement.elements("book");
		// System.out.println(bookements.size());
		for (int i = 0; i < bookements.size(); i++) {
			List<Element> deleElements = bookements.get(i).elements("price");
			for (int j = 0; j < deleElements.size(); j++) {
				// System.out.println(deleElements.size());
				// System.out.println(deleElements.get(j));
				deleElements.get(j).getParent().remove(deleElements.get(j));
			}
		}

		writeBackDoc(doc);
	}

	@Test
	public void test05() throws Exception {
		// ���²���,����name=java���鼮�ļ۸�Ϊ45Ԫ��

		SAXReader saxReeader = new SAXReader();
		Document doc = saxReeader.read("xml_Test_01.xml");
		Element rootElement = doc.getRootElement();
		List<Element> bookList = rootElement.elements("book");
		for (int i = 0; i < bookList.size(); i++) {
			List<Element> elements = bookList.get(i).elements("name");

			for (int j = 0; j < elements.size(); j++) {
				if (elements.get(j).getText().equals("JAVA�������")) {
					elements.get(j).getParent().element("price").setText("45Ԫ");
					System.out.println(elements.get(j).getParent()
							.element("price"));
					elements.get(j).getParent().element("price")
							.setName("newPrice");// �޸ı�ǩ����
				}
			}
		}
		// �����Զ��������������������
		writeBackDoc(doc);

		System.out.println(File.separator);
		System.out.println(File.pathSeparator);
	}

}
