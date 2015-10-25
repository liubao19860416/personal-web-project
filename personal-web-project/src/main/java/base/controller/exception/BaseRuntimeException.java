package base.controller.exception;

import java.io.Serializable;

/**
 * 基础的RuntimeException类
 * 
 * @author Liubao
 * @2014年12月1日
 *
 */
public abstract class BaseRuntimeException extends RuntimeException implements  Serializable{

    private static final long serialVersionUID = -704044988102432490L;

  //一个抽象方法，子类必须覆写的
    public abstract Throwable getCause();
    
    public BaseRuntimeException() {
        super();
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }
    
    protected BaseRuntimeException(String message) {
        super(message);
    }
    
    public BaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public BaseRuntimeException(String message, Throwable cause,boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
