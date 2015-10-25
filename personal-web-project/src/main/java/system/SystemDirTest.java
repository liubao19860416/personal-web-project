package system;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.saike.grape.base.BaseEnvironment;

public class SystemDirTest {
    
    /**
     * src the source array.
     * srcPos starting position in the source array.
     * dest the destination array.
     * destPos starting position in the destination data.
     * length the number of array elements to be copied.
     * 
     * 测试数组拷贝方法:从指定位置，拷贝指定个数的的元素到指定数组的指定位置
     */
    @Test
    public void testName() throws Exception {
        Object src[]=new String[]{"111","222","333","444"};
        int srcPos=1;
        Object dest[] =new String[5];
        int destPos=1;
        int length=2;
        System.arraycopy(src, srcPos, dest, destPos, length);
        System.out.println(dest.length);
        System.out.println(Arrays.toString(dest));
    }
    
    public static void main(String[] args) {
        Map<String, String> envs = System.getenv();
        String userdir = System.getProperty("user.dir");
        userdir=userdir.substring(userdir.lastIndexOf("\\"));
        System.out.println("0:"+userdir);//D:\eclipse-20141015\workspace\personal-web-project
        String path = "";
        //String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        System.out.println("1:"+path);
        path=Thread.currentThread().getContextClassLoader().getResource("").getPath(); 
        System.out.println("2:"+path);//D:/eclipse-20141015/workspace/personal-web-project/target/classes/
        path=BaseEnvironment.class.getClassLoader().getResource("").getPath();  
        System.out.println("3:"+path);///D:/eclipse-20141015/workspace/personal-web-project/target/classes/
        path=BaseEnvironment.class.getResource("/").getFile().toString(); 
        System.out.println("4:"+path);//D:/eclipse-20141015/workspace/personal-web-project/target/classes/
        path=BaseEnvironment.class.getResource(".").getFile().toString(); 
        System.out.println("5:"+path);//D:/eclipse-20141015/workspace/project-base/target/classes/com/saike/grape/base/
        path=BaseEnvironment.class.getResource("").getFile().toString(); 
        System.out.println("6:"+path);//D:/eclipse-20141015/workspace/project-base/target/classes/com/saike/grape/base/
        path=BaseEnvironment.class.getResource("../").getFile().toString();
        System.out.println("7:"+path);//D:/eclipse-20141015/workspace/project-base/target/classes/com/saike/grape/
        System.out.println("7:"+path);//D:/eclipse-20141015/workspace/project-base/target/classes/com/saike/grape/
   
        
        ///D:/eclipse-20141015/apache-tomcat-7.0.47/wtpwebapps/personal-web-project/WEB-INF/classes/
        path="/D:/eclipse-20141015/apache-tomcat-7.0.47/wtpwebapps/personal-web-project/WEB-INF/classes/";
        //path=path.substring(0, path.indexOf("/WEB-INF/classes"));
        //path=path.substring(0, path.indexOf(File.separator+"WEB-INF"+File.separator+"classes"));
        //path=path.substring(path.lastIndexOf("/"));
        
        System.out.println("8:"+path);//D:/eclipse-20141015/workspace/project-base/target/classes/com/saike/grape/
        //\
        System.out.println(File.separator);
        System.out.println(System.getProperty("file.separator"));
        
       //基本不用
        System.out.println("===============================");
        Map<String, String> envs2 = System.getenv();
        for (String key1 : envs2.keySet()) {
            String value1 = envs2.get(key1);
            System.out.println(key1+"====>"+value1);
        }
        System.out.println("===============================");
        Properties p = System.getProperties();
        Enumeration<Object> e = p.elements();
        while(e.hasMoreElements()){
            String key = (String) e.nextElement();  
            System.out.println(key+"====>"+p.getProperty(key));
        }
        System.out.println("===============================");
        
        String fileSeparator = System.getProperty("file.separator");
        System.out.println(fileSeparator);
        fileSeparator=File.separator;
        System.out.println(fileSeparator);
        
        
        Properties prop = System.getProperties();
        for (Object str : prop.keySet()) {
            String value = (String) prop.get(str);
            System.out.println(str + "--->" + value);
        }
        
        Properties pp = System.getProperties(); 
        Enumeration<?> e2 = pp.propertyNames();
        while (e2.hasMoreElements()) {
            String key = (String) e2.nextElement();
             System.out.println(key+"--->"+pp.getProperty(key));
             pp.setProperty("key", pp.getProperty(key)); 
            try {
                pp.store(new FileOutputStream(""), "java_property files!");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        
        //几个方法测试
        String str = "你好。。。";
        try {
            System.out.write(str.getBytes());
            str = "\r";
            System.out.write(str.getBytes());
        } catch (IOException e3) {
            e3.printStackTrace();
        }

        System.getProperties().list(System.out);

        
        

    }
}
