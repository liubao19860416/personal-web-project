package _test;
/**
 * 测试实体  子类
 * @author admin
 *
 */
@SuppressWarnings("serial")
public class Son extends Fathor implements Animal{
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Son [age=" + age + ", toString()=" + super.toString() + "]";
    }

    public Son() {
        super();
        System.out.println("Son构造方法执行了。。。");
    }

    // void test(int i) {
    // i++;
    // System.out.println(i);
    // }

    // 主方法做测试使用
    public static void main(String[] args) {
        Fathor b = new Son();// 先执行父类Fathor的构造方法，再执行子类Son的构造方法；
        b.test(0);// 子类中存在该方法，则执行子类中覆写的方法；
        b.test((byte) 0);// 如果不存在，则到父类中去寻找，执行父类中对应的方法；如果父类也不存在，则会报错；
    }
}
