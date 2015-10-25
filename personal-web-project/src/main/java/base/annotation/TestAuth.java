package base.annotation;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.core.annotation.AnnotationUtils;

public class TestAuth {
    public static void main(String[] args) throws Throwable, SecurityException {
        
        Class <?> c = null ;
        c = Class.forName("base.annotation.TestAuthClass") ;
        Method toM = c.getMethod("getStr") ;  // 找到getStr()方法
        // 判断是否是指定的Annotation
        Auth authAnnotation = null ;
        if(toM.isAnnotationPresent(Auth.class)){
            authAnnotation = toM.getAnnotation(Auth.class) ; // 得到指定的Annotation
            String value = authAnnotation.value() ;    // 取出设置的value
            System.out.println("value = " + value) ;
            boolean privileged = authAnnotation.isPrivileged();
            System.out.println("privileged = " + privileged) ;
            
        }
        
        // Annotation authAnnotation = method.getAnnotation(Auth.class);
        // Auth authAnnotation = handlerMethod.getMethodAnnotation(Auth.class);
//        Auth authAnnotation = AnnotationUtils
//                .findAnnotation(method, Auth.class);

        if (authAnnotation != null) {
            // TODO 获取注解的一些相关信息
            Map<String, Object> attributes = AnnotationUtils
                    .getAnnotationAttributes(authAnnotation);
            for (String key : attributes.keySet()) {
                Object value = attributes.get(key);
                System.out.println(key + "====获取注解属性方式1=====>>>" + value.toString());
            }
        }
    }
}
