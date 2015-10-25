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

		// 读取节点；

		// 首先需要到dom4j的jar包；
		// 获得解析器对象；
		SAXReader saxReader = new SAXReader();
		// 获得document对象；
		Document document = saxReader.read("xml_Test_01.xml");
		// 获取根元素，然后逐级获取其他元素，并对其进行操作；注意：只能逐级获取List类型集合元素；
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

		// 读取属性值；
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
		// 添加节点；<ISBN>
		SAXReader saxreader = new SAXReader();
		Document doc = saxreader.read("xml_Test_01.xml");

		Element rootElement = doc.getRootElement();
		// 使用帮助累，创建元素对象；
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
		// 获取输入良好的输出元素格式format；
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");// 指定输出编码格式；

		// FileWriter默认采用系统编码，所以使用FileOutputStream
		// XMLWriter xmlwriter=new XMLWriter(new
		// FileWriter("xml_test_dom4j.xml"),format);
		XMLWriter xmlwriter = new XMLWriter(new FileOutputStream(
				"xml_test_dom4j.xml"), format);
		xmlwriter.write(doc);
		xmlwriter.close();
	}

	// @Test
	public void test04() throws Exception {

		// 删除操作；
		SAXReader saxreader = new SAXReader();
		Document doc = saxreader.read("xml_Test_01.xml");
		Element rootElement = doc.getRootElement();
		// 只能逐级获取其中的元素，不能跨级获取；所以只能先获取book，再获取price；
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
		// 更新操作,更新name=java的书籍的价格为45元；

		SAXReader saxReeader = new SAXReader();
		Document doc = saxReeader.read("xml_Test_01.xml");
		Element rootElement = doc.getRootElement();
		List<Element> bookList = rootElement.elements("book");
		for (int i = 0; i < bookList.size(); i++) {
			List<Element> elements = bookList.get(i).elements("name");

			for (int j = 0; j < elements.size(); j++) {
				if (elements.get(j).getText().equals("JAVA基础编程")) {
					elements.get(j).getParent().element("price").setText("45元");
					System.out.println(elements.get(j).getParent()
							.element("price"));
					elements.get(j).getParent().element("price")
							.setName("newPrice");// 修改标签名；
				}
			}
		}
		// 不可以丢掉，否则无数据输出；
		writeBackDoc(doc);

		System.out.println(File.separator);
		System.out.println(File.pathSeparator);
	}

}
