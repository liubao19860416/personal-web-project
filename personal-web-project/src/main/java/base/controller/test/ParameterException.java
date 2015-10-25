package base.controller.test;

/**
 * 自定义的异常类2--RuntimeException
 * 
 * @author Liubao
 * @2014年12月1日
 * 
 */
@SuppressWarnings("serial")
public class ParameterException extends RuntimeException {

    public ParameterException(Throwable t) {
        super(t);
    }

    public ParameterException(String string) {
        super(string);
    }

}
