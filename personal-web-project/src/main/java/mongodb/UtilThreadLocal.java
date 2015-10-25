package mongodb;

import java.lang.reflect.Constructor;

/**
 * 本地线程变量对象了类型
 */
public class UtilThreadLocal<T> extends ThreadLocal<T> {
    /** 参数集合 */
    Object[] obj;
    /** 实例化构造函数 */
    Constructor<T> construct;

    /**
     * 本地变量的class 构造函数的参数
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public UtilThreadLocal(Class clazz, Object... args)
            throws NoSuchMethodException, SecurityException {
        // this.obj = obj;
        Class[] clazzs = null;
        /** new 获取参数class供获取构造函数用 */
        if (args != null)
            if (args.length != 0) {
                clazzs = new Class[args.length];
                for (int i = 0; i < args.length; i++) {
                    clazzs[i] = args[i].getClass();
                }
            }
        this.construct = clazz.getConstructor(clazzs);
    }

    /**
     * 如果当前线程没有对象创建一个新对象
     */
    public T newGet() {
        T tar = super.get();
        if (tar == null) {
            try {
                tar = construct.newInstance(obj);
                super.set(tar);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tar;
    }

    protected T initialValue() {
        return null;
    }

}
