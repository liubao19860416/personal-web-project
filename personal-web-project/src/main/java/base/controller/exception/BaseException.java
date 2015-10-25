package base.controller.exception;

import java.io.Serializable;

/**
 * 基础的Exception类
 * 
 * @author Liubao
 * @2014年12月1日
 * 
 */
public abstract class BaseException extends Exception implements Serializable {

    private static final long serialVersionUID = -704044988102432490L;
    
    //一个抽象方法，子类必须覆写的
    public abstract Throwable getCause();

    public BaseException() {
        super();
    }
    
    protected BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BaseException(Throwable cause) {
        super(cause);
    }
    
    public BaseException(String message, Throwable cause,boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
