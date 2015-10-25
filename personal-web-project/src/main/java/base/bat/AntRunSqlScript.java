package base.bat;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;
/**
 * sql脚本语法检查代码(其实还是执行sql脚本) OK
 * 
 * @author Liubao
 * @2014年11月30日
 *
 */
public class AntRunSqlScript {

    /**
     * 用ant的sqlExec来检测多个sql文件的语法和编码是否正确（每个sql文件可能包含多个sql语句），代码如下：
     * 但这样处理，在异常中没有抓到那些有语法错误和乱码的sql文件，请问大牛们该如何处理?
     */
    public static boolean runSqlScript(String db, String sqlPath, String fileName,String logPath) {
        String dbPort ="3306";
        String ip = "127.0.0.1";
        //String ip = "localhost";
        String urlPrefix = "jdbc:mysql://localhost:3306/";
        String urlSurfix = "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
        String dbPWD = "root";
        String dbUserid = "root";

        if (!(sqlPath.endsWith("/") || sqlPath.endsWith(File.separator))) {
            sqlPath += File.separator;
        }
        SQLExec sqlExec = new SQLExec();
        sqlExec.setDriver("com.mysql.jdbc.Driver");
        sqlExec.setUrl(urlPrefix + db+urlSurfix);
        sqlExec.setUserid(dbUserid);
        sqlExec.setPassword(dbPWD);
        //sqlExec.setDelimiter("GO");
        sqlExec.setDelimiter(";");
        sqlExec.setSrc(new File(sqlPath + fileName));
        // "continue", "stop"  "abort"
        sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "continue")));
        // sqlExec.setOnerror((SQLExec.OnError)(EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
        sqlExec.setAutocommit(true);
        if (logPath != null) {
            sqlExec.setPrint(true);
            if (!(logPath.endsWith("/") || logPath.endsWith(File.separator))) {
                logPath += File.separator;
            }
            sqlExec.setOutput(new File(logPath + ip + "_" + dbPort + "_" + db+ "_" + fileName + ".log"));
        }
        //必须要加上的
        sqlExec.setProject(new Project());
        try {
             sqlExec.execute();
             System.out.println("成功更新"+ip+":"+dbPort+";dbName="+db+"脚本"+fileName);
             //updateLog.append("更新"+ip+":"+dbPort+";dbName="+db+"脚本"+fileName+"\n");
            return true;
        } catch (Exception e) {
            // updateLog.append("服务器："+ip+e.getMessage());
            System.out.println("更新失败" + ip + ":" + dbPort + ";dbName=" + db+ "脚本" + fileName);
            return false;
        }
    }
    
    
    public static void main(String[] args) {
        boolean result = AntRunSqlScript.runSqlScript("grape20", "d:/temp/", "data.sql", null);
        System.out.println("执行结果为："+result);
    }

}
