package base.annotation;

public class TestAuthClass {

    private String str;

    @Auth(isPrivileged = false, name = "test", value = "测试")
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
