import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xml_donparse {

	// @Test
	public void DOM_test1() throws Exception {

		/**
		 * ����java se�е�api֪ʶ����Dom�����Ĺ������£�
		 * 
		 * 
		 * 
		 * 1����ȡ�ڵ��ı����ݣ�
		 */

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse("xml_Test_01.xml");

		NodeList elementsByTagName = doc.getElementsByTagName("name");

		Node item = elementsByTagName.item(2);// book.1.name
		System.out.println(item.getNodeName() + ":" + item.getNodeValue());
		System.out.println(item.getTextContent());
		// System.out.println(item.getFirstChild().getNodeName() + ":"
		// + item.getFirstChild().getNodeValue());

		NodeList childNodes = item.getChildNodes();// book.name

		System.out.println(childNodes.item(0).getNodeName() + ":"
				+ childNodes.item(0).getNodeValue());

	}

	// @Test
	public void test01() throws Exception {
		// ��ȡ����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder
				.parse(new FileInputStream("xml_Test_01.xml"));

		NodeList elementsByTagName = document.getElementsByTagName("book");

		Element item = (Element) elementsByTagName.item(0);// ǿ��ת��,��չ������
		System.out.println(item.getAttribute("id"));

	}

	// @Test
	public void test02() throws Exception {
		// ��ӡ����Ԫ�ؽڵ�����ƣ�

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder
				.parse(new FileInputStream("xml_Test_01.xml"));
		// NodeList nodeEle=document.getElementsByTagName("book");
		// System.out.println(nodeEle.getLength());
		// System.out.println(nodeEle.item(0).getNodeValue());
		// System.out.println(nodeEle.item(1).getTextContent());
		// System.out.println(nodeEle.item(2).getUserData("id"));

		printDocument(document);

	}

	// �ݹ鷽��,��ӡdocument�е����е�Ԫ�ؽڵ����ƣ�
	// Node��Document�ĸ����ͣ������������ʹ��Node���ʹ���
	public void printDocument(Node doc) {
		if (doc.ELEMENT_NODE == doc.getNodeType()) {
			// ˵����Ԫ�ؽڵ�
			System.out.println(doc.getNodeName());
		}

		NodeList list = doc.getChildNodes();// ���Ԫ���ӽڵ㼯�ϣ�
		for (int i = 0; i < list.getLength(); i++) {
			Node nd = list.item(i);
			printDocument(nd);
		}
	}

	// @Test
	public void test03() throws Exception {

		// ����������<ISBN>100110</ISBN>�ڵ������id=1��

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new FileInputStream("xml_Test_01.xml"));

		// ��ȡ�丸�ڵ㣬��Ҫ��ӵĽڵ�����ϣ�
		NodeList elementsByTagName = doc.getElementsByTagName("book");
		for (int i = 0; i < elementsByTagName.getLength(); i++) {
			// ��ÿһ��ָ�����͵� Ԫ��������Ժ����ݣ�
			Element createElement = doc.createElement("ISBN");
			createElement.setAttribute("id", "01" + i);
			createElement.setTextContent("100110" + i);
			elementsByTagName.item(i).appendChild(createElement);
			// System.out.println(createElement.getNodeName());

		}
		// ��д����������Ϊdocument��Node���ͣ�
		writeBack(doc);

	}

	// ������ø�ʽ���ã�

	// д�غ���
	public void writeBack(Node doc) throws Exception {

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		// �ؼ���䣻
		transformer.transform(new DOMSource(doc), new StreamResult(
				"xml_Test_01_coyp.xml"));

	}

	// ��ȡdoc��������
	public static Document getDoc() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = builder.parse(new FileInputStream("xml_Test_01.xml"));
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		return doc;
	}

	//@Test
	public void test04() {
		// ɾ���ڵ�priceԪ�غ�ָ����priceԪ�أ�
		Document doc = getDoc();

		NodeList elementsByTagName = doc.getElementsByTagName("price");
		// ɾ�����е�priceԪ��
		// for (int i = 0; i < elementsByTagName.getLength(); i++) {
		// Node oldChild = elementsByTagName.item(i);
		// // ɾ���ӽڵ�
		// oldChild.getParentNode().removeChild(oldChild);
		// i--;
		// }
		// ɾ��ָ���۸��Ԫ��
		for (int i = 0; i < elementsByTagName.getLength(); i++) {
			Node oldChild = elementsByTagName.item(i);
			// ɾ���ӽڵ�
			if (oldChild.getTextContent().equals("33Ԫ")) {
				oldChild.getParentNode().removeChild(oldChild);
				i--;
			}
		}

		try {
			writeBack(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test05() throws Exception {
		//���½ڵ㣻���¼۸�Ϊ55Ԫ��
		
		Document  doc=getDoc();
		NodeList elementsByTagName = doc.getElementsByTagName("price");
		for (int i = 0; i < elementsByTagName.getLength(); i++) {
			elementsByTagName.item(i).setTextContent("55Ԫ");
		}
		
		writeBack(doc);
		
	}

}
