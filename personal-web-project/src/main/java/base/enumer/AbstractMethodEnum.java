package base.enumer;

enum Color3 implements Print {
    RED {
        public String getColor() {
            return "红色";
        }
    },
    GREEN {
        public String getColor() {
            return "绿色";
        }
    },
    BLUE {
        public String getColor() {
            return "黄色";
        }
    };
    
    //定义抽象方法
    public abstract String getColor();
}

/**
 * 定义抽象接口或者抽象方法
 * 
 * @author Liubao
 * @2015年3月23日
 * 
 */
public class AbstractMethodEnum {
    public static void main(String args[]) {
        for (Color3 c : Color3.values()) {
            System.out.print(c.getColor() + ",");
        }
    }
};