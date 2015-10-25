


import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class xml_junitTest {

	//@Test
	public void testRead() {

		System.out.println("hello world");
	}

	@Test
	public void testDom4j() {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read("xml_Test_01.xml");
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Element rootElement = document.getRootElement();
		List<Element> list = rootElement.elements("book");
		for (Element element1 : list) {
			System.out.println(element1.getText());
		}
	}

}
