package base.controller.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统提示信息结果集封装类-基础类
 * @param <T>
 */
public class ResultInfo<T> implements Serializable{
    private static final long serialVersionUID = 740596852737587069L;
    
    public static final int RESULT_INFO = 0;// 普通提示信息
    public static final int RESULT_SUCCESS = 1;// 成功
    public static final int RESULT_FAIL = 2;// 失败
    public static final int RESULT_WARN = 3;// 警告

    private int type;// 消息提示类型
    private int messageCode;// 提示代码
    private String message;// 提示信息

    /**
     * 提示信息明细列表，只需要是字符串即可
     */
    private List<String> detailList=new ArrayList<String>(); 
    /**
     * 提交后得到到业务数据信息从而返回给页面
     */
    private Map<String, Object> dataResultMap = new HashMap<String, Object>();
    /**
     * 提交后得到到业务数据信息从而返回给页面List，这个可以生省略，将其放到map中即可，只是为了方便
     */
    private List<T> dataResultList=new ArrayList<T>(); 
    /**
     * 提示消息对应操作的序号，方便用户查找问题，通常用于在批量提示信息中标识记录序号
     */
    private int index;

    /**
     * 判断操作是否成功或者失败
     */
    public boolean isSuccess() {
        if (this.type == RESULT_SUCCESS) {
            return true;
        }
        return false;
    }
    
    public ResultInfo() {
    }

    /**
     * 构造函数,根据提交信息代码messageCode获取提示信息
     * final int type
     */
    public ResultInfo(final int type, int messageCode, String message) {
        this.type = type;
        this.messageCode = messageCode;
        this.message = message;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<String> detailList) {
        this.detailList = detailList;
    }

    public Map<String, Object> getDataResultMap() {
        return dataResultMap;
    }

    public void setDataResultMap(Map<String, Object> dataResultMap) {
        this.dataResultMap = dataResultMap;
    }

    public List<T> getDataResultList() {
        return dataResultList;
    }

    public void setDataResultList(List<T> dataResultList) {
        this.dataResultList = dataResultList;
    }

}