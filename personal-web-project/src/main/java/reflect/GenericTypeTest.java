package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import base.entiy.User;

public class GenericTypeTest {

    
    @Test
    public void testName() throws Exception {
        User user=new User();
        Method method1=null;
        //HandlerMethod handlerMethod=(HandlerMethod) handler;
        HandlerMethod handlerMethod=new HandlerMethod(user, method1);
        
        Object bean = handlerMethod.getBean();
        Method method = handlerMethod.getMethod();
        
        //找action方法是否是标注了responseBody注解，类似操作自定义注解信息@Auth，Authorized，授权的
        ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method,ResponseBody.class);
        
        if(responseBodyAnn!=null){
            //TODO 执行自定义操作
            //return this.handlerResolveException(exceptionResultInfo,request, response);
        }
        
        //获取该方法的返回值信息
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        
        //class base.controller.SimpleUrlController
        Class<?> beanType = handlerMethod.getBeanType();
        /**
         * TODO 获取定义的指定的注解信息，进行相应的控制，如权限控制拦截
         */
        //handlerMethod.getMethodAnnotation(annotationType);
        
        //方法全部信息
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        //代理对象
        MethodParameter returnType = handlerMethod.getReturnType();
        //代理对象
        Class<? extends HandlerMethod> class1 = handlerMethod.getClass();
        class1.getGenericSuperclass();//Object
    }
    
}
