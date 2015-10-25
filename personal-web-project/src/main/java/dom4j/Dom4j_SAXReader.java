package dom4j;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4j_SAXReader {
    
    public static void main(String[] args) {
        try {
            Dom4j_SAXReader.xmlReader();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static String[] xmlReader() throws Exception {

        // StringBuilder sb=new StringBuilder();
        String[] tempStr = new String[100];
        int index = 0;

        SAXReader reader = new SAXReader();
        //Document doc = reader.read("project/base/dom4j_xml/infoList.xml");
        Document doc = reader.read("infoList.xml");

        Element rootE = doc.getRootElement();

        List elements = rootE.elements();
        for (Object obj : elements) {
            // System.out.println(obj+":");
        }

        List<Element> singersE = rootE.elements("singer");
        for (int i = 0; i < singersE.size(); i++) {
            // System.out.println(singersE.get(i).elements("name"));
            List<Element> namesE = singersE.get(i).elements("name");
            for (int j = 0; j < namesE.size(); j++) {
                // System.out.println(namesE.get(j).getName() + ":"
                // + namesE.get(j).getText());
                if (namesE.get(j).getText() != ""
                        && namesE.get(j).getText() != null) {
                    tempStr[index++] = namesE.get(j).getText();
                    // sb.append(namesE.get(j).getText());
                }
            }
        }
        String[] newtempStr = new String[index];
        for (int i = 0; i < index; i++) {
            newtempStr[i] = tempStr[i];
        }
        return newtempStr;
        // return sb.toString();
    }

}
