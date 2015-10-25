package base.controller.test;

/**
 * 自定义异常类--Exception
 * 
 * @author Liubao
 * @2014年12月1日
 * 
 */
public class TestException extends Exception {

    private static final long serialVersionUID = -3682512965407142434L;

    public TestException() {
        super();
    }

    public TestException(String message) {
        super(message);
    }

    public TestException(Throwable cause) {
        super(cause);
    }
    
    public TestException(String message1, String message2) {
        super(message1 + message2);
    }

    public TestException(String message, Throwable cause) {
        super(message, cause);
    }

}
