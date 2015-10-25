package string;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import thread.thread2.User;

public class StringUtilsTest {

    /**
     * 可以不用复写toString方法了
     */
    @Test
    public void testToString() throws Exception {
        
        User user=new User("liubap", 22);
        String toString = ToStringBuilder.reflectionToString(user,
                ToStringStyle.SHORT_PREFIX_STYLE);
        System.out.println(toString);
        
        toString = ToStringBuilder.reflectionToString(user,
                ToStringStyle.MULTI_LINE_STYLE);
        System.out.println(toString);
    }

}
