package base.enumer;
/**
 * 性别枚举类2
 * 
 * @author Liubao
 * @2014年12月1日
 *
 */
public enum Gender2 {
    //抽象方法
    // public abstract String getName();

    WOMEN("男"), MAN("女");

    public String getValue() {
        return String.valueOf(this.value);
    }

    private String value;

    private Gender2() {
    }

    private Gender2(String value) {
        this.value = value;
    }

}
