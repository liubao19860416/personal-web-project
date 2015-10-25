package _test;

import java.text.MessageFormat;

import org.junit.Test;

public class MessageFormatTet {

    @Test
    public void testFormat() throws Exception {
        String pattern = "这个是我的格式化数据测试类{0}-{1}-{2}";
        MessageFormat messageFormat = new MessageFormat(pattern);
        Object[] arguments = new Object[] { "1", new String("刘保"), new Integer(3) };
        StringBuffer format = messageFormat.format(arguments, new StringBuffer(), null);
        System.out.println(format.toString());
    }

}
