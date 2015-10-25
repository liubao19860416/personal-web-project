package base.controller.test;
/**
 * service接口
 * 
 * @author Liubao
 * @2014年12月1日
 *
 */
public interface TestService {
    
    public void exception(Integer id) throws Exception;

    public void testDao(Integer id) throws Exception;
}
