package base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring容器中的ApplicationContext对象的帮助类
 * 
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    @Autowired
    private static ApplicationContext applicationContext;

    /**
     * 此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量。
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 这是一个便利的方法，帮助我们快速得到一个BEAN
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        checkApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 这是一个便利的方法，帮助我们快速得到一个BEAN
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> clazz) {
        checkApplicationContext();
        return (T) applicationContext.getBean(clazz);
    }

    /**
     * 清除applicationContext静态变量.
     */
    public static void cleanApplicationContext() {
        applicationContext = null;
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext未注入,请在spring配置文件中中注入该bean");
        }
    }
}
