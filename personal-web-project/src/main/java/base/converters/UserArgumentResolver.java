package base.converters;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import base.entiy.ActiveUser;

public class UserArgumentResolver implements WebArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
            NativeWebRequest webRequest) throws Exception {
        // 如果参数类型是ActiveUser，对形参进行解析
        if (methodParameter.getParameterType().equals(ActiveUser.class)) {
            // 将session中的ActiveUser取出赋值给action方法的形参
            ActiveUser activeUser = (ActiveUser) webRequest.getAttribute(
                    Config.ACTIVEUSER_KEY, RequestAttributes.SCOPE_SESSION);
            return activeUser;// 将从session中取到的activeuser赋值给action方法的形参（activeUser）
        }
        return UNRESOLVED;
    }

}
