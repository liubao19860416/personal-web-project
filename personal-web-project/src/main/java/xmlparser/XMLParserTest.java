package xmlparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import _test.Person;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

/**
 * Xstream操作Xml与Json
 * 
 * @author Liubao
 * @2014年11月17日
 * 
 */
public class XMLParserTest {
    
    @Test
    public void testWriteObject2JSON() throws Exception {
        Person p = new Person();
        p.setBirthday(new Date());
        p.setPid(1111l);
        p.setPname("liubao");
        p.setDescription("描述信息");
        String str = XMLHelperUtils.writeObject2JSON(p);
        System.out.println(str);
    }
    
    @Test
    public void testSimpleObject2XML() throws Exception {
        Person p=new Person();
        p.setBirthday(new Date());
        p.setPid(1111l);
        p.setPname("liubao");
        p.setDescription("描述信息");
        String string = XMLHelperUtils.simpleObject2XML(p);
        System.out.println(string);
    }
    
    @Test
    public void testSimpleXML2Object() throws Exception {
        String str="<Person><pid>1111</pid><pname>liubao</pname><birthday>2014-11-18 09:56:35.890 CST</birthday><description>描述信息</description></Person>";
        Person p=(Person) XMLHelperUtils.simpleXML2Object(str,new Person() );
        System.out.println(p.toString());
    }

    /**
     * 读取指定名称的多个xml文件，获取该xml文件对应的xml格式的字符串
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testAddToXMLFile() throws Exception {
        XStream xstream = new XStream();

        // 设置xml根标签的别名为person，否则默认为test.*
        xstream.alias("person", Person.class);

        PersistenceStrategy strategy = new FilePersistenceStrategy(new File("D:\\temp"), xstream);
        List<Person> list = new XmlArrayList(strategy);
        // 保存数据
        list.add(new Person("张三", 13l));
        list.add(new Person("李四", 21l));
        list.add(new Person("王五", 17l));
        list.add(new Person("招六", 27l));
    }

    /**
     * 不删除，就相当于读取了数据了！！！只能读取指定名称的文件
     * 但是不能知道他的具体长度
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteXmlFile() throws Exception {
        PersistenceStrategy strategy = new FilePersistenceStrategy(new File("D:\\temp"));
        List<?> list = new XmlArrayList(strategy);
        for (Iterator<?> it = list.iterator(); it.hasNext();) {
            System.out.println((Person) it.next());
            // 删除对象序列化文件
            // it.remove();
        }
    }

    /**
     * xml的格式是有很多限制要求的！！！
     * 
     * @throws Exception
     */
    @Test
    public void testReadXML() throws Exception {
        XStream xstream = new XStream();
        xstream.alias("person", Person.class);
        InputStream input = new FileInputStream(new File("D:\\temp\\person.xml"));
        // xstream.fromXML(input);
        ObjectInputStream in = xstream.createObjectInputStream(input);
        Object readObject = in.readObject();
        while (readObject != null) {
            System.out.println((Person) readObject);
            readObject = in.readObject();
        }
    }

    /**
     * 将xml格式的字符串转换为对应的对象属性值
     * 
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        // 初始化对象
        XStream xstream = new XStream();
        Person person = new Person();
        person.setBirthday(new Date());
        person.setDescription("描述信息。。。");
        person.setPassword("11111");
        person.setPname("Pname");
        person.setPid(1111l);
        // XML序列化
        String xml = xstream.toXML(person);
        System.out.println(xml);

        // Json反序列转换为对象
        Person bean = (Person) xstream.fromXML(xml);
        System.out.println(bean.toString());

    }

}
