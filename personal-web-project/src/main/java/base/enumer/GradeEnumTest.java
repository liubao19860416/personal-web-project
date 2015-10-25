package base.enumer;

import org.junit.Test;
/**
 * 分数级别测试枚举类
 * 
 * @author Liubao
 * @2014年12月2日
 *
 */
public class GradeEnumTest {

    @Test
    public void test02() {
        System.out.println(Grade.A.getGrade());
        System.out.println(Grade.A.toLocalString());
        System.out.println(Grade.D.getGrade());
        System.out.println(Grade.D.toLocalString());
    }
}

enum Grade {
    A("90--100") {
        public String toLocalString() {
            return "优";
        }
    },
    B("80--90") {
        public String toLocalString() {
            return "良";
        }
    },
    C("70--80") {
        public String toLocalString() {
            return "中";
        }
    },
    D("60--70") {
        public String toLocalString() {
            return "及格";
        }
    },
    E("<60") {
        public String toLocalString() {
            return "不及格";
        }
    };
    private Grade(String value) {
        this.value = value;
    }

    private Grade() {
    }

    private String value;

    public String getGrade() {
        return value;
    }

    public abstract String toLocalString();

}
