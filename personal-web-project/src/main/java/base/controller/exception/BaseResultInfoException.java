package base.controller.exception;

import base.controller.result.ResultInfo;

/**
 * 自定义ResultInfo格式异常类
 */
public class BaseResultInfoException extends BaseException {

    private static final long serialVersionUID = 3301680489210172490L;
    
    // 系统统一使用的结果类
    private ResultInfo resultInfo;
    
    @Override
    public Throwable getCause() {
     // TODO Auto-generated method stub
        return null;
    }

    public BaseResultInfoException(ResultInfo resultInfo) {
        super(resultInfo.getMessage());
        this.resultInfo = resultInfo;
    }

    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

   

}
