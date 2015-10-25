package system;

public class RuntimeDemo01 {
    public static void main(String args[]) {
        Runtime run = Runtime.getRuntime();
        System.out.println("JVM:" + run.maxMemory());
        System.out.println("JVM:" + run.freeMemory());
        String str = "Hello " + "World" + "!!!" + "\t" + "Welcome " + "To "
                + "MLDN" + "~";
        System.out.println(str);
        for (int x = 0; x < 1000; x++) {
            str += x;
        }
        System.out.println("JVM:" + run.freeMemory());
        run.gc();
        System.out.println("JVM:" + run.freeMemory());
    }
};