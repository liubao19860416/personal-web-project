package base.controller.handler;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import base.controller.exception.BaseResultInfoException;
import base.controller.result.ResultInfo;


/**
 * 系统全局异常器,主要用来处理ajax请求返回json数据的异常处理，和其他的常规的异常信息页面跳转处理等
 */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver, Ordered {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	//json转换器
	private HttpMessageConverter<BaseResultInfoException> jsonMessageConverter;
	
	//优先级别调整属性，当前最低权限（Integer.MAXVALUE）
	private int order = Ordered.LOWEST_PRECEDENCE;
	
	/**
	 * handler为controller实例
	 * exception为异常信息,用来处理页面跳转的异常信息？
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception exception) {
		//输出异常信息
		logger.error("系统全局异常器,输出异常信息...",exception);
		
		//返回结果是jsp页面或者json字符串，捕获的异常是自定义的异常；
		BaseResultInfoException exceptionResultInfo=null;
        if(exception instanceof BaseResultInfoException){
            //如果是系统自定义则直接转换
            exceptionResultInfo = (BaseResultInfoException)exception;
        }else{
            //如果不是将异常信息构造成未知错误信息
            ResultInfo resultInfo = new ResultInfo();
            //设置类型为失败
            resultInfo.setType(ResultInfo.RESULT_FAIL);
            resultInfo.setMessage("未知识错误，请与管理员联系。");
            exceptionResultInfo = new BaseResultInfoException(resultInfo); 
        }

		//获取controller的方法实例(springmvc对当次请求controller的封装)
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		//获取方法对象
		Method method = handlerMethod.getMethod();
		//找action方法是否是标注了responseBody注解
		ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method,ResponseBody.class);
		
		if(responseBodyAnn!=null){
		    //说明方法返回的ResponseBody注解的,处理返回类型为json异常信息
			return this.handlerResolveException(exceptionResultInfo,request, response);
		}
		
		//获取错误代码，如果异常代码为106，需要挑转到登录页面，否则 跳转错误页面
		int messageCode = exceptionResultInfo.getResultInfo().getMessageCode();
		String message = exceptionResultInfo.getResultInfo().getMessage();
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(messageCode == 106){
			//需要挑转到登录页面，当前设置页面为登录页面
			modelAndView.setViewName("/base/login");
		}else{
		    //设置错误页面
			modelAndView.setViewName("/base/error");
			modelAndView.addObject("exceptionResultInfo", exceptionResultInfo);
		}
		
		modelAndView.addObject("message", message);
		return modelAndView;

	}
	
	
	/**
	 * 抽取的具体的处理返回数据类型为json的异常信息方法
	 */
	public ModelAndView handlerResolveException(BaseResultInfoException exceptionResultInfo,HttpServletRequest request,HttpServletResponse response) {
		
		//将exceptionResultInfo转换为json,构造http输出对象
		HttpOutputMessage httpOutputMessage = new ServletServerHttpResponse(response);
		try {
		    //将异常信息转为json,将异常信息输出json
			jsonMessageConverter.write(exceptionResultInfo, MediaType.APPLICATION_JSON,httpOutputMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * HttpMessageConverter转换器对象的set和get方法
	 */
	public HttpMessageConverter<BaseResultInfoException> getJsonMessageConverter() {
		return jsonMessageConverter;
	}

	public void setJsonMessageConverter(HttpMessageConverter<BaseResultInfoException> jsonMessageConverter) {
		this.jsonMessageConverter = jsonMessageConverter;
	}

    /**
	 * 排序需要实现的接口
	 */
    @Override
    public int getOrder() {
        return order;
    }
    
    public void setOrder(int order) {
        this.order = order;
    }
	
}
