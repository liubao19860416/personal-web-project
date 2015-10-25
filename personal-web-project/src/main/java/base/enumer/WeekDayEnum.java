package base.enumer;
/**
 * 星期的枚举类2
 * @author Liubao
 * @2014年12月2日
 *
 */
public enum WeekDayEnum {
    SUNDAY("星期日", 0), MONDAY("星期一", 1), TUESDAY("星期二", 2), WEDNESDAY("星期三", 3), THURSDAY(
            "星期四", 4), FRIDAY("星期五", 5), SATURDAY("星期六", 6);

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        //单个对象
        WeekDayEnum.getWeekDayValue(WeekDayEnum.FRIDAY);
        WeekDayEnum[] values = WeekDayEnum.values();
        for (WeekDayEnum weekDayEnum : values) {
            System.out.println(weekDayEnum.name() +"===>>"+ weekDayEnum.ordinal() +"===>>"+ weekDayEnum);
            weekDayEnum.getWeekDayValue(weekDayEnum);
            System.out.println();
        }
    }
    
    //获取其对应的中文星期名称
    private String weekDayValue =null;
    
    public String getWeekDayValue() {
        return this.weekDayValue;
    }

    public void setWeekDayValue(String weekDayValue) {
        this.weekDayValue = weekDayValue;
    }

    private WeekDayEnum() {
    }
    
    private WeekDayEnum(String name) {
    }

    private WeekDayEnum(String name, int ordinal) {
    }

    public static void getWeekDayValue(WeekDayEnum weekDayEnum) {
        switch (weekDayEnum) {
        case MONDAY:
            System.out.println("MONDAY is selected。。。");
            break;
        case TUESDAY:
            System.out.println("TUESDAY is selected。。。");
            break;
        case WEDNESDAY:
            System.out.println("WEDNESDAY is selected。。。");
            break;
        case THURSDAY:
            System.out.println("THURSDAY is selected。。。");
            break;
        case FRIDAY:
            System.out.println("FRIDAY is selected。。。");
            break;
        case SATURDAY:
            System.out.println("SATURDAY is selected。。。");
            break;
        default:
            System.out.println("SUNDAY is selected。。。");
            break;
        }
    }
    
}
