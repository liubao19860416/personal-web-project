package base.enumer;

import org.junit.Test;


public class WeekDayEnum2 {
    
    @Test
    public void test02() throws Exception {
        WeekDay[] values = WeekDay.values();
        for (WeekDay weekDay : values) {
            //System.out.println(weekDay.getInfo());
            System.out.println(weekDay.getWeekDay());
        }

    }

    @Test
    public void test01() {
        System.out.println(WeekDay.MON.getInfo());
        System.out.println(WeekDay.TUS.getInfo());
        System.out.println(WeekDay.WED.getInfo());
        System.out.println(WeekDay.THU.getInfo());
        System.out.println(WeekDay.FRI.getInfo());
        System.out.println(WeekDay.SAT.getInfo());
        System.out.println(WeekDay.SUN.getInfo());
    }
}

enum WeekDay {
    MON("星期一") {
        @Override
        public String getInfo() {
            return getWeekDay();
        }
    },
    TUS("星期二") {
        public String getInfo() {
            return getWeekDay();
        }
    },
    WED("星期三") {
        @Override
        public String getInfo() {
            return getWeekDay();
        }
    },
    THU("星期四") {
        @Override
        public String getInfo() {
            return getWeekDay();
        }
    },
    FRI("星期五") {
        @Override
        public String getInfo() {
            return getWeekDay();
        }
    },
    SAT("星期六") {
        @Override
        public String getInfo() {
            return getWeekDay();
        }
    },
    SUN("星期日") {
        @Override
        public String getInfo() {
            return getWeekDay();
        }
    };

    private String weekDay;

    private WeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public abstract String getInfo();

}
