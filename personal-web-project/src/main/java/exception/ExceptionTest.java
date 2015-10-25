package exception;

import org.junit.Test;


public class ExceptionTest {

    public static void main(String[] args) {
//import org.geotoolkit.util.UnsupportedImplementationException;
//         new UnsupportedImplementationExceptio("");
    }

    /**
     * 抛出不支持操作异常信息
     * 
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {
        throw new UnsupportedOperationException("当前操作未实现...");
    }
    
    @Test
    public void test02() throws Exception {
//        throw KeeperException.UnimplementedException("当前操作未实现...");
    }

}
