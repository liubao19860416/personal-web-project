package xmlparser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import base.entiy.ActiveUser;
/**
 * 报错 不能使用！
 * @author Liubao
 * @2014年11月25日
 *
 */
public class PullHelper {
    
    //main测试方法
    public static void main(String[] args) throws Exception {
        List<ActiveUser> activeUsers = new ArrayList<ActiveUser>();
        for (int i = 0; i <10; i++) {
            ActiveUser activeUser = new ActiveUser();
            activeUser.setUserid("userid"+i);
            activeUser.setUsername("username"+i);
            activeUser.setGroupid("groupid"+i);
            activeUser.setGroupname("groupname"+i);
            activeUser.setSysid("sysid"+i);
            activeUser.setSysmc("sysmc"+i);
            activeUsers.add(activeUser);
        }
        
        String fileName="d:/temp/activeUsers.xml";
        
        //将list转换为xml文件输出
        writeList2Xml(activeUsers, fileName);
        
        System.out.println("转换完成了。。。");
        
    }
    
    
    /**
     * 将指定的xml文件转换为List对象数组
     */
    public static List<ActiveUser> parseXml2List(String fileName) throws Exception {
        // 实例化对象
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        // InputStream in = PullHelper.class.getResourceAsStream("/" +fileName);
        InputStream in = new FileInputStream(fileName);
        parser.setInput(in, "utf-8");
        List<ActiveUser> activeUsers = null;
        ActiveUser activeUser = null;

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_DOCUMENT) {
                activeUsers = new ArrayList<ActiveUser>();
            } else if (eventType == XmlPullParser.START_TAG) {
                if (parser.getName().equals("activeUser")) {
                    activeUser = new ActiveUser();
                } else if (parser.getName().equals("userId")) {
                    activeUser.setUserid(parser.nextText());
                } else if (parser.getName().equals("username")) {
                    activeUser.setUsername(parser.nextText());
                } else if (parser.getName().equals("groupId")) {
                    activeUser.setGroupid(parser.nextText());
                } else if (parser.getName().equals("groupName")) {
                    activeUser.setGroupname(parser.nextText());
                }else if (parser.getName().equals("sysId")) {
                    activeUser.setSysid(parser.nextText());
                }else if (parser.getName().equals("sysMc")) {
                    activeUser.setSysmc(parser.nextText());
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                if (parser.getName().equals("activeUser")) {
                    activeUsers.add(activeUser);
                }
            }
            eventType = parser.next();
        }
        return activeUsers;
    }

    /**
     * 将指定的对象List转换为xml文件
     */
    public static void writeList2Xml(List<ActiveUser> activeUsers, String fileName)
            throws Exception {
        //解决异常信息的方法
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance(
                System.getProperty(XmlPullParserFactory.PROPERTY_NAME), Thread
                        .currentThread().getContextClassLoader().getClass());
        
        //XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlSerializer serializer = factory.newSerializer();
        // String fullPath = PullHelper.class.getResource("/" +fileName).getFile();
        OutputStream output = new FileOutputStream(fileName);
        serializer.setOutput(output, "utf-8");
        serializer.startDocument("utf-8", true);
        //serializer.startTag(null, "activeUsers");
        for (ActiveUser activeUser : activeUsers) {
            //一个对象开始标签
            serializer.startTag(null, "activeUser");
            //对象属性
            serializer.startTag(null, "userId");
            serializer.text(activeUser.getUserid());
            serializer.endTag(null, "userId");
            serializer.startTag(null, "username");
            serializer.text(activeUser.getUsername());
            serializer.endTag(null, "username");
            serializer.startTag(null, "groupId");
            serializer.text(activeUser.getGroupid());
            serializer.endTag(null, "groupId");
            serializer.startTag(null, "groupName");
            serializer.text(activeUser.getGroupname());
            serializer.endTag(null, "groupName");
            serializer.startTag(null, "sysId");
            serializer.text(activeUser.getSysid());
            serializer.endTag(null, "sysId");
            serializer.startTag(null, "sysMc");
            serializer.text(activeUser.getSysmc());
            serializer.endTag(null, "sysMc");
            //一个对象结束标签
            serializer.endTag(null, "activeUser");
        }
        //serializer.endTag(null, "activeUsers");
        serializer.endDocument();
    }
}
