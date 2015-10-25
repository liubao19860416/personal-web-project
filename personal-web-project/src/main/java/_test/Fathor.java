package _test;

/**
 * 父类
 * 
 * @author Liubao
 * @2014年9月17日
 */
@SuppressWarnings("serial")
public class Fathor implements Animal{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Fathor [name=" + name + "]";
    }

    public Fathor() {
        super();
        System.out.println("Fathor构造方法执行了。。。");
    }

    void test(int i) {
        System.out.print(i);
    }

    // 注释后，子类可以正常执行；因为byte可以自动向上类型转换为int
    // void test(byte b) {
    // System.out.print(b);
    // }
}
