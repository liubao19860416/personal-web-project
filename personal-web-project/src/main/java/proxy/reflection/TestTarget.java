package proxy.reflection;

public class TestTarget implements TestInterface {
    public String doTest1(String aStr, int aNumber) {
        return aStr + aNumber;
    }

    public void doTest2(String[] strs) {
        throw new RuntimeException("I don't know what to do in doTest2");
    }
}
