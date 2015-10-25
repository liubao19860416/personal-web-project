package base.controller.test;

import org.springframework.stereotype.Repository;

/**
 * DAO测试类
 * 
 * @author Liubao
 * @2014年12月1日
 * 
 */
@Repository("testDao")
public class TestDao {
    public void exception(Integer id) throws Exception {
        switch (id) {
        case 1:
            throw new TestException("12", "dao12");
        case 2:
            throw new TestException("22", "dao22");
        case 3:
            throw new TestException("32", "dao32");
        case 4:
            throw new TestException("42", "dao42");
        case 5:
            throw new TestException("52", "dao52");
        default:
            throw new ParameterException("Dao Parameter Error");
        }
    }
}
