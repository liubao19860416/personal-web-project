package base.controller.exception;

/**
 * 自定义异常类:session超时
 */
public class SessionTimeoutException extends BaseException {

    private static final long serialVersionUID = 1426605052598578120L;

    public SessionTimeoutException(String msg) {
        super(msg);
    }

    @Override
    public Throwable getCause() {
        // TODO Auto-generated method stub
        return null;
    }
}
