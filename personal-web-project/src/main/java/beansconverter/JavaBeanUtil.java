package beansconverter;


/**
 * javaBean的基本构成字符串转换方法
 */
public class JavaBeanUtil {

    private static final char SEPARATOR = '_';

    public static void main(String[] args) {
        System.out.println(JavaBeanUtil.toUnderlineString("ISOCertifiedStaff"));
        System.out.println(JavaBeanUtil.toCamelCaseString("iso_certified_staff", true));
        System.out.println(JavaBeanUtil.toCamelCaseString("iso_certified_staff"));
        System.out.println(JavaBeanUtil.getSetterMethodName("userID"));
        System.out.println(JavaBeanUtil.getGetterMethodName("userID"));
        System.out.println(JavaBeanUtil.toCamelCaseString("site_Id"));
    }

    /**
     * 将驼峰样式字符串转换为属性样式字符串<br>
     * (例:ISObranchNo -> iso_branch_no)<br>
     */
    public static String toUnderlineString(String inputString) {
        if (inputString == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            boolean nextUpperCaseFlag = true;
            if (i < (inputString.length() - 1)) {
                nextUpperCaseFlag = Character.isUpperCase(inputString.charAt(i + 1));
            }

            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!flag || !nextUpperCaseFlag) {
                    if (i > 0){
                        sb.append(SEPARATOR);
                    }
                }
                flag = true;
            } else {
                flag = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 将属性样式字符串转换为驼峰样式字符串<br>
     * (例:branch_no -> branchNo )<br>
     */
    public static String toCamelCaseString(String inputString) {
        return toCamelCaseString(inputString, false);
    }

    /**
     * 将驼峰字段转成属性字符串<br>
     * (例:branch_no -> branchNo )<br>
     */
    public static String toCamelCaseString(String inputString,boolean firstCharacterUppercase) {
        if (inputString == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            switch (c) {
            case '_':
            case '-':
            case '@':
            case '$':
            case '#':
            case ' ':
            case '/':
            case '&':
                if (sb.length() > 0) {
                    nextUpperCase = true;
                }
                break;
            default:
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
                break;
            }
        }
        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }
        return sb.toString();
    }

    /**
     * 将属性转换成标准set方法名字符串<br>
     * 只判断首字母大小写
     */
    public static String getSetterMethodName(String property) {
        StringBuilder sb = new StringBuilder();
        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        sb.insert(0, "set");
        return sb.toString();
    }

    /**
     * 将属性转换成标准get方法名字符串<br>
     * 只判断首字母大小写
     */
    public static String getGetterMethodName(String property) {
        StringBuilder sb = new StringBuilder();
        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        sb.insert(0, "get");
        return sb.toString();
    }

}
