package base.dwr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
/**
 * 默认访问地址
 * http://localhost:8080/personal-web-project/dwr/index.html/
 * 
 * @author Liubao
 * @2014年11月24日
 *
 */

@Service(value="dwrService")
public class DwrServiceImpl implements DwrService {

    /**
     * 测试方法1
     */
    public String testdwr() throws Exception {
        return "helloworld !";
    }

    /**
     * 测试方法2
     */
    public String testdwr2(String name) throws Exception {
        return "helloworld " + name;
    }

    /**
     * 获取年度，获取近5年
     */
    public List<Map<String, String>> businessyear() throws Exception {
        // 当前年
        int year = Integer.parseInt(MyUtil.get_YYYY(MyUtil.getDate()));
        // 定义了一个list对象，list里边是map
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        // 将近5年放入list
        for (int i = year; i >= year - 5; i--) {
            Map<String, String> index = new HashMap<String, String>();
            index.put("info", i + "");
            index.put("value", i + "");
            result.add(index);
            System.out.println(i);
        }
        // 将list返回
        return result;
    }


}
