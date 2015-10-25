package system;

public class RuntimeDemo03 {
    public static void main(String args[]) {
        Runtime run = Runtime.getRuntime();
        Process p = null;
        try {
            p = run.exec("notepad.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
        //关闭操作
        p.destroy();
    }
};