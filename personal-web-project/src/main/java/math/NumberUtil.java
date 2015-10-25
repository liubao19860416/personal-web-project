package math;

/**
 * 数字工具类
 */
public final class NumberUtil {

    private NumberUtil() {
    }

    /**
     * 判断数字类型
     */
    public static boolean isNumber(String value) {
        boolean flag = true;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if ((c < '0' || c > '9')) {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
