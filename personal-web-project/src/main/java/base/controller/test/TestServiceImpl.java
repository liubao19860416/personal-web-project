package base.controller.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * service实现类
 * 
 * @author Liubao
 * @2014年12月1日
 * 
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    
    @Resource
    private TestDao testDao;

    public void exception(Integer id) throws Exception {
        switch (id) {
        case 1:
            throw new TestException("11", "service11");
        case 2:
            throw new TestException("21", "service21");
        case 3:
            throw new TestException("31", "service31");
        case 4:
            throw new TestException("41", "service41");
        case 5:
            throw new TestException("51", "service51");
        default:
            throw new ParameterException("Service Parameter Error");
        }
    }

    @Override
    public void testDao(Integer id) throws Exception {
        testDao.exception(id);
    }
}