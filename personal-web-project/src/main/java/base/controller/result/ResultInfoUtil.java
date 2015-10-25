package base.controller.result;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统结果集工具类
 * @param <T>
 */
public class ResultInfoUtil {
    
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ResultInfoUtil.class);
    
    //===============================Fail信息提示======================================
    /**
     * 创建错误结果集，objs中是错误或者成功记录信息的条数等信息
     * @param <T>
     */
    public static <T> ResultInfo<T> createResultFail(int messageCode,Object[] objs) {
        String message = null;
        if (objs == null) {
            message = ResourcesUtil.getResourcesValue( messageCode + "");
        } else {
            message = ResourcesUtil.getResourcesValue(messageCode + "", objs);
        }
        return new ResultInfo<T>(ResultInfo.RESULT_FAIL, messageCode, message);
    }
    /**
     * 添加创建失败详情信息描述list，这个是单一的list信息，如失败信息或者成功信息
     * @param <T>
     */
    public static <T> ResultInfo<T> createResultFail(int messageCode,Object[] objs,List<String> detailList) {
        ResultInfo<T> resultInfo=createResultFail(messageCode, objs);
        if (detailList != null) {
            resultInfo.setDetailList(detailList);
        }
        return resultInfo;
    }
    /**
     * 添加创建失败详情信息描述list，这个可以是单一的list信息，也可以是多种信息，放到map中即可
     * @param <T>
     */
    public static <T> ResultInfo<T> createResultFail(int messageCode,Object[] objs,Map<String, Object> dataResultMap) {
        ResultInfo<T> resultInfo=createResultFail(messageCode, objs);
        if (dataResultMap != null) {
            resultInfo.setDataResultMap(dataResultMap);
        }
        return resultInfo;
    }

    //===============================warn信息提示======================================
    /**
     * 创建警告提示结果集，objs中是错误或者成功记录信息的条数等信息
     * @param <T>
     */
    public static <T> ResultInfo<T> createResultWarn( int messageCode, Object[] objs) {
        String message = null;
        if (objs == null) {
            message = ResourcesUtil.getResourcesValue( messageCode + "");
        } else {
            message = ResourcesUtil.getResourcesValue( messageCode + "", objs);
        }
        return new ResultInfo<T>(ResultInfo.RESULT_WARN, messageCode, message);
    }
    /**
     * 添加创建警告详情信息描述list，这个可以是单一的list信息，也可以是多种信息，放到map中即可
     * @param <T>
     */
    public static <T> ResultInfo<T> createResultWarn(int messageCode,Object[] objs,List<String> detailList) {
        ResultInfo<T> resultInfo=createResultWarn(messageCode, objs);
        if (detailList != null) {
            resultInfo.setDetailList(detailList);
        }
        return resultInfo;
    }
    /**
     * 添加创建警告详情信息描述list，这个可以是单一的list信息，也可以是多种信息，放到map中即可
     * @param <T>
     */
    public static <T> ResultInfo<T> createResultWarn(int messageCode,Object[] objs,Map<String, Object> dataResultMap) {
        ResultInfo<T> resultInfo=createResultWarn(messageCode, objs);
        if (dataResultMap != null) {
            resultInfo.setDataResultMap(dataResultMap);
        }
        return resultInfo;
    }
    
   //===============================Success信息提示======================================
    /**
     * 创建成功提示结果集，objs中是错误或者成功记录信息的条数等信息
     * @param <T>
     */
    public static <T> ResultInfo<T> createResultSuccess(int messageCode,Object[] objs) {
        String message = null;
        if (objs == null) {
            message = ResourcesUtil.getResourcesValue( messageCode + "");
        } else {
            message = ResourcesUtil.getResourcesValue( messageCode + "", objs);
        }
        return new ResultInfo<T>(ResultInfo.RESULT_SUCCESS, messageCode, message);
    }
    /**
     * 添加创建成功详情信息描述list，这个可以是单一的list信息，也可以是多种信息，放到map中即可
     * @param <T>
     */
    public static <T> ResultInfo<T> createResultSuccess(int messageCode,Object[] objs,List<String> detailList) {
        ResultInfo<T> resultInfo=createResultSuccess(messageCode, objs);
        if (detailList != null) {
            resultInfo.setDetailList(detailList);
        }
        return resultInfo;
    }
    /**
     * 添加创建成功详情信息描述list，这个可以是单一的list信息，也可以是多种信息，放到map中即可
     * @param <T>
     */
    public static <T> ResultInfo<T> createResultSuccess(int messageCode,Object[] objs,Map<String, Object> dataResultMap) {
        ResultInfo<T> resultInfo=createResultSuccess(messageCode, objs);
        if (dataResultMap != null) {
            resultInfo.setDataResultMap(dataResultMap);
        }
        return resultInfo;
    }
    
    //===============================Info信息提示======================================
    /**
     * 创建普通提示信息结果集，objs中是错误或者成功记录信息的条数等信息
     * @param <T>
     */
    public static <T> ResultInfo<T> createResultInfo(int messageCode, Object[] objs) {
        String message = null;
        if (objs == null) {
            message = ResourcesUtil.getResourcesValue(messageCode + "");
        } else {
            message = ResourcesUtil.getResourcesValue( messageCode + "", objs);
        }
        return new ResultInfo<T>(ResultInfo.RESULT_INFO, messageCode, message);
    }
    /**
     * 添加创建普通提示详情信息描述list，这个可以是单一的list信息，也可以是多种信息，放到map中即可
     * @param <T>
     */
    public static <T> ResultInfo<T> createResultInfo(int messageCode,Object[] objs,List<String> detailList) {
        ResultInfo<T> resultInfo=createResultInfo(messageCode, objs);
        if (detailList != null) {
            resultInfo.setDetailList(detailList);
        }
        return resultInfo;
    }
    /**
     * 添加创建普通提示详情信息描述list，这个可以是单一的list信息，也可以是多种信息，放到map中即可
     * @param <T>
     */
    public static <T> ResultInfo<T> createResultInfo(int messageCode,Object[] objs,Map<String, Object> dataResultMap) {
        ResultInfo<T> resultInfo=createResultInfo(messageCode, objs);
        if (dataResultMap != null) {
            resultInfo.setDataResultMap(dataResultMap);
        }
        return resultInfo;
    }

    //===============================Excepion信息提示======================================
    /**
     * 抛出自定义ResultInfo结果集异常类
     */
    public static <T> void throwExcepion(ResultInfo<T> resultInfo) throws ResultInfoException {
        throw new ResultInfoException(resultInfo);
    }
    /**
     * 抛出自定义ResultInfo结果集异常类，带描述信息详情list参数
     */
    public static <T> void throwExcepion(ResultInfo<T> resultInfo,List<String> detailList) throws ResultInfoException {
        if (resultInfo == null) {
            resultInfo=new ResultInfo<T>();
        }
        if (detailList == null) {
            resultInfo.setDetailList(detailList);
        }
        throw new ResultInfoException(resultInfo);
    }
    /**
     * 抛出自定义ResultInfo结果集异常类，带描述信息详情list参数
     */
    public static <T> void throwExcepion(ResultInfo<T> resultInfo,Map<String, Object> dataResultMap) throws ResultInfoException {
        if (resultInfo == null) {
            resultInfo=new ResultInfo<T>();
        }
        if (dataResultMap == null) {
            resultInfo.setDataResultMap(dataResultMap);
        }
        throw new ResultInfoException(resultInfo);
    }


    //===============================扩展类-SubmitResultInfo信息提示======================================
    /**
     * 创建提交结果信息
     * @param <T>
     */
    public static <T> SubmitResultInfo<T> createSubmitResultInfo(ResultInfo<T> resultInfo) {
        return new SubmitResultInfo<T>(resultInfo);
    }

    /**
     * 创建提交结果信息，包括明细List集合信息
     * @param <T>
     */
    public static <T> SubmitResultInfo<T> createSubmitResultInfo(ResultInfo<T> resultInfo, List<String> detailList) {
        if (resultInfo == null) {
            resultInfo=new ResultInfo<T>();
        }
        if (detailList == null) {
            resultInfo.setDetailList(detailList);
        }
        return new SubmitResultInfo<T>(resultInfo);
    }
    /**
     * 创建提交结果信息，包括明细List集合信息
     * @param <T>
     */
    public static <T> SubmitResultInfo<T> createSubmitResultInfo(ResultInfo<T> resultInfo, Map<String, Object> dataResultMap) {
        if (resultInfo == null) {
            resultInfo=new ResultInfo<T>();
        }
        if (dataResultMap == null) {
            resultInfo.setDataResultMap(dataResultMap);
        }
        return new SubmitResultInfo<T>(resultInfo);
    }

}
