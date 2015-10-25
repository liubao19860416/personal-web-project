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
		 * 利用java se中的api知识，即Dom解析的过程如下：
		 * 
		 * 
		 * 
		 * 1、读取节点文本内容；
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
		// 读取属性
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder
				.parse(new FileInputStream("xml_Test_01.xml"));

		NodeList elementsByTagName = document.getElementsByTagName("book");

		Element item = (Element) elementsByTagName.item(0);// 强制转换,扩展方法；
		System.out.println(item.getAttribute("id"));

	}

	// @Test
	public void test02() throws Exception {
		// 打印所有元素节点的名称；

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

	// 递归方法,打印document中的所有的元素节点名称；
	// Node是Document的父类型；所以这里可以使用Node类型代表；
	public void printDocument(Node doc) {
		if (doc.ELEMENT_NODE == doc.getNodeType()) {
			// 说明是元素节点
			System.out.println(doc.getNodeName());
		}

		NodeList list = doc.getChildNodes();// 获得元素子节点集合；
		for (int i = 0; i < list.getLength(); i++) {
			Node nd = list.item(i);
			printDocument(nd);
		}
	}

	// @Test
	public void test03() throws Exception {

		// 添加书下面的<ISBN>100110</ISBN>节点和属性id=1；

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new FileInputStream("xml_Test_01.xml"));

		// 获取其父节点，将要添加的节点添加上；
		NodeList elementsByTagName = doc.getElementsByTagName("book");
		for (int i = 0; i < elementsByTagName.getLength(); i++) {
			// 给每一个指定类型的 元素添加属性和内容；
			Element createElement = doc.createElement("ISBN");
			createElement.setAttribute("id", "01" + i);
			createElement.setTextContent("100110" + i);
			elementsByTagName.item(i).appendChild(createElement);
			// System.out.println(createElement.getNodeName());

		}
		// 回写函数；参数为document的Node类型；
		writeBack(doc);

	}

	// 如何设置格式良好；

	// 写回函数
	public void writeBack(Node doc) throws Exception {

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		// 关键语句；
		transformer.transform(new DOMSource(doc), new StreamResult(
				"xml_Test_01_coyp.xml"));

	}

	// 获取doc对象函数；
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
		// 删除节点price元素和指定的price元素；
		Document doc = getDoc();

		NodeList elementsByTagName = doc.getElementsByTagName("price");
		// 删除所有的price元素
		// for (int i = 0; i < elementsByTagName.getLength(); i++) {
		// Node oldChild = elementsByTagName.item(i);
		// // 删除子节点
		// oldChild.getParentNode().removeChild(oldChild);
		// i--;
		// }
		// 删除指定价格的元素
		for (int i = 0; i < elementsByTagName.getLength(); i++) {
			Node oldChild = elementsByTagName.item(i);
			// 删除子节点
			if (oldChild.getTextContent().equals("33元")) {
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
		//更新节点；更新价格为55元；
		
		Document  doc=getDoc();
		NodeList elementsByTagName = doc.getElementsByTagName("price");
		for (int i = 0; i < elementsByTagName.getLength(); i++) {
			elementsByTagName.item(i).setTextContent("55元");
		}
		
		writeBack(doc);
		
	}

}
