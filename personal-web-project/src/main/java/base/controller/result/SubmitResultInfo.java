package base.controller.result;

/**
 * 系统提交结果类,是对ResultInfo类的扩展
 * @param <T>
 */
public class SubmitResultInfo<T> extends ResultInfo<T> {
    private static final long serialVersionUID = 5819969718860110161L;

    private ResultInfo<T> resultInfo;

    public SubmitResultInfo(ResultInfo<T> resultInfo) {
        this.resultInfo = resultInfo;
    }

    public ResultInfo<T> getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(ResultInfo<T> resultInfo) {
        this.resultInfo = resultInfo;
    }

}
