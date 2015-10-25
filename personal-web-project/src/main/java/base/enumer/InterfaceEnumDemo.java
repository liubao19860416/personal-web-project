package base.enumer;

/**
 * 定义枚举接口
 * 
 * @author Liubao
 * @2015年3月23日
 * 
 */
interface Print {
    public String getColor();
}

enum Color4 implements Print {
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
}

public class InterfaceEnumDemo {
    public static void main(String args[]) {
        for (Color4 c : Color4.values()) {
            System.out.print(c.getColor() + "，");
        }
    }
};