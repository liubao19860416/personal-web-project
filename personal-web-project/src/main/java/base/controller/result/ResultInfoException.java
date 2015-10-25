package base.controller.result;

/**
 * 自定义ResultInfo结果集异常类
 */
public class ResultInfoException extends Exception {
    private static final long serialVersionUID = 6857011098443615748L;
    
    // 系统统一使用的结果类
	@SuppressWarnings("rawtypes")
    private ResultInfo resultInfo;

	public <T> ResultInfoException(ResultInfo<T> resultInfo) {
		super(resultInfo.getMessage());
		this.resultInfo = resultInfo;
	}

	@SuppressWarnings("unchecked")
    public <T> ResultInfo<T> getResultInfo() {
		return this.resultInfo;
	}

	public <T> void setResultInfo(ResultInfo<T> resultInfo) {
		this.resultInfo = resultInfo;
	}

}
