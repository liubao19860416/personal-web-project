package base.bat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Quick Batch File (De)Compiler，可以将任何BAT、CMD批处理脚本编译为EXE文件
 * Quick Batch File (De)Compiler使用非常简单：
 * Quickbfc 文件名.bat 文件名.exe（将批处理命令编译为可执行文件）
 * quickbfd 文件名.exe 文件名.bat（将可执行文件反编译为批处理命令）
 * 
 * @author Liubao
 * @2014年11月30日
 *
 */
public class ExecuteBatFile {
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        String batFilePath="d:/temp/insertsql.bat";
        //batFilePath="base/bat/insertsql.bat";
        //batFilePath="C:\\windows\\NOTEPAD.exe";
        /**
         * 这是通过java执行cmd的方式
         * Runtime.getRuntime().exec("sqlplus username/pwd@tnsname @你的sql的脚本文件") 
         * 需要sqlplus环境，和正确的tnsname配置，如果是远程的话，这里基本和命令行的方式一样了
         * cmd="sqlplus test/test@orcl @d://sql//database.sql";
         */
        Process process = null;
        try {
            // 解决不弹框只需要“start”后面加一个参数“/b”就行：
            // Runtime.getRuntime().exec("cmd.exe /C start /b D:\\test.bat");
            
            /*ProcessBuilder pb = new ProcessBuilder();
            pb.directory(new File("d:/temp/"));
            pb.command("insertsql.bat");
            pb.redirectErrorStream(true);
            Process p1 = pb.start();
            int waitFor1 = p1.waitFor();
            int result1 = process.exitValue();
            if (result1 == 0) {
                System.out.println("执行完成.");
            } else {
                System.out.println("执行失败.");
            }*/
            
            process = Runtime.getRuntime().exec(batFilePath);
            int waitFor2 = process.waitFor();
            int result2 = process.exitValue();
            if (result2 == 0) {
                System.out.println("执行完成.");
            } else {
                System.out.println("执行失败.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //如果要指定bat中的内容, 可以用io来实现 相当于对普通文件的读取写入！
        BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String str = null; 
        try {
            while ((str = read.readLine()) != null) { 
                System.out.println(str); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
}
