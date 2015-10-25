package base.enumer;
/**
 * 性别枚举类1
 * 
 * @author Liubao
 * @2014年12月1日
 *
 */
public enum Gender {
    //两个枚举对象
    MAN {
        public String getName() {
            return "男";
        }
    },
    WOMEN {
        public String getName() {
            return "女";
        }
    };
    
    //抽象方法
    public abstract String getName();

}
