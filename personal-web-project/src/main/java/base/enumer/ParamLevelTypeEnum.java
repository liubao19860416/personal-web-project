package base.enumer;

public enum ParamLevelTypeEnum {

    isExist("退出"), isNull("为null空"), isEmpty("为空串");

    private String levelType;

    private ParamLevelTypeEnum(String levelType) {
        this.levelType = levelType;
    }

    @Override
    public String toString() {
        return this.levelType;
    }

    public static void main(String[] args) {
        ParamLevelTypeEnum[] types = ParamLevelTypeEnum.values();
        for (ParamLevelTypeEnum type : types) {
            //type.ordinal()：相当于是脚标，从0开始；
            System.out.println(type.name() +"===>>"+ type.ordinal() +"===>>"+ type);
        }
        System.out.println(String.valueOf(ParamLevelTypeEnum.isEmpty));
        System.out.println(ParamLevelTypeEnum.isExist);
        System.out.println(ParamLevelTypeEnum.isNull);
    }

}
