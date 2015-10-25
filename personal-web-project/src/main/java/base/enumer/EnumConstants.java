package base.enumer;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举常量定义类
 */
public final class EnumConstants {
    
    
    //main测试方法
    public static void main(String[] args) {
        //Enum<?> enumByValue = EnumConstants.getEnumByValue(Gear.class, "A");
        //System.out.println(enumByValue.name());//AUTO
        //System.out.println(enumByValue.toString());//A
        
        //测试
        Gender[] values = Gender.values();
        for (Gender gender : values) {
            System.out.println(gender.ordinal());
            System.out.println(gender.name());
        }
        System.out.println(Gender.MAN);
        System.out.println(Gender.WOMEN);
        
    }

    // hidden from instantiating
    private EnumConstants() {
    }

    private static final Map<Class<? extends Enum<?>>, Enum<?>[]> mapEnumTypes = new HashMap<Class<? extends Enum<?>>, Enum<?>[]>();

    static {
        mapEnumTypes.put(MaintenancePeriodUnit.class,
                MaintenancePeriodUnit.class.getEnumConstants());

        mapEnumTypes.put(SparePartGroupedSelectMode.class,
                SparePartGroupedSelectMode.class.getEnumConstants());
        
        mapEnumTypes.put(Gear.class,
                Gear.class.getEnumConstants());
    }

    // 保养周期单位
    public static enum MaintenancePeriodUnit {
        NONE("-"), // 非周期性
        KM("km"), // 按公里数
        DAY("day"); // 按日期（天数）

        private String unit;

        private MaintenancePeriodUnit(String unit) {
            this.unit = unit;
        }

        @Override
        public String toString() {
            return this.unit;
        }
    }

    // 配件选择模式
    public static enum SparePartGroupedSelectMode {
        MUST("must"), // 必选
        CHECKED("checked"), // 选中状态（可选）
        UNCHECKED("unchecked"); // 非选中状态（可选）

        private String selectMode;

        private SparePartGroupedSelectMode(String selectMode) {
            this.selectMode = selectMode;
        }

        @Override
        public String toString() {
            return this.selectMode;
        }
    }

    // 车挡类型
    public static enum Gear {
        AUTO("A"), // 自动
        MANUAL("M"), // 手动
        MANUAL_PLUS_AUTO("B"), // 手自一体
        NA("-"); // N/A 未知

        private String type;

        private Gear(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return this.type;
        }
    }

    /**
     * 获取枚举类对应的属性值value的静态方法
     * @param enumClass
     * @param value
     * @return
     */
    public static Enum<?> getEnumByValue(Class<? extends Enum<?>> enumClass,
            String value) {

        if (enumClass == null || !enumClass.isEnum()) {
            throw new IllegalArgumentException("Argument enumClass is null!!");
        }

        if (value == null || "".equals(value)) {
            return null;
        }

        for (Class<? extends Enum<?>> eclass : mapEnumTypes.keySet()) {
            if (eclass.equals(enumClass)) {
                for (Enum<?> e : mapEnumTypes.get(eclass)) {
                    if (e.toString().equals(value)) {
                        return e;
                    }
                }
            }
        }

        throw new IllegalArgumentException("Enum class \""
                + enumClass.getName() + "\"" + " with value \"" + value + "\""
                + " is not supported!!");
    }

}
