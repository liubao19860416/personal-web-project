package base.bat;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;
/**
 * 执行sql脚本-- OK
 * 
 * @author Liubao
 * @2014年11月30日
 *
 */
public class AntExecSqlFile {
    
    private static final String driveClass="com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/grape20?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
    private static final String userID="root";
    private static final String pwd = "root";
    
    private static  SQLExec sqlExec ;
    //构造方法
    static {
        //初始化对象
        sqlExec = new SQLExec();
        // 设置数据库参数
        sqlExec.setDriver(driveClass);
        sqlExec.setUrl(url);
        sqlExec.setUserid(userID);
        sqlExec.setPassword(pwd);
    }

    public static boolean execSqlFile(String sqlFile) {
        boolean flag =true;
        try {
            //加载sql脚本
            sqlExec.setSrc(new File(sqlFile));
            //设置输出相关错误类型参数等
            sqlExec.setOnerror((SQLExec.OnError)(EnumeratedAttribute.getInstance(SQLExec.OnError.class,"abort")));
            // 设置是否输出
            sqlExec.setPrint(true); 
            // 输出到文件 sql.out 中；不设置该属性，默认输出到控制台
            sqlExec.setOutput(new File("d:/temp/sql.out"));
            // 要指定这个属性，不然会出错
            sqlExec.setProject(new Project()); 
            //开始执行
            sqlExec.execute();
        } catch (BuildException e) {
            flag=false;
            e.printStackTrace();
        }
        
        return flag;
    }
    
    
    public static void main(String[] args) {
        boolean result = AntExecSqlFile.execSqlFile("d:/temp/data.sql");
       System.out.println("执行结果为："+result);
    }
    
    
}
