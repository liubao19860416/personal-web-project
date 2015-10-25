import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class xml_saxparse {

	@Test
	public void test01() throws Exception {
		// 工厂类，获取sax解析器对象；
		SAXParserFactory factory1 = SAXParserFactory.newInstance();
		// 获得sax解析器；
		SAXParser newSAXParser = factory1.newSAXParser();
		// 获取读取xml内容的对象；
		XMLReader xmlReader = newSAXParser.getXMLReader();
		// 获取对应的时间处理器；
		xmlReader.setContentHandler(new MyHandler());

		xmlReader.parse("xml_Test_01.xml");

	}
}

class MyHandler extends DefaultHandler {

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("startDocument");
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("endDocument");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		System.out.println("startElement:"+qName);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		System.out.println("endElement:"+qName);
	}

	@Override
	//文本的时候，需要执行的方法；
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		System.out.println("characters:"+new String(ch,start,length));
	}

}