package base.controller.exception;
/**
 * 自定义的类型转换异常类
 * 
 * @author Liubao
 * @2014年12月1日
 *
 */
public class TranFailException extends BaseException {

    private static final long serialVersionUID = -5025319135888177908L;
    
    private String errorCode = "DEFAULT";

    @Override
    public Throwable getCause() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public TranFailException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }


}
