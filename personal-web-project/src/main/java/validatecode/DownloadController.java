package validatecode;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import base.CustomizedPropertyPlaceholderConfigurer;
/**
 * 下载主页面跳转Controller
 */
@Controller
@RequestMapping(value = "/app/bygj")
public class DownloadController {

    @RequestMapping(value = "/download")
    public ModelAndView download(Model model, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("download");
        //mav.addObject("message", "DownloadController的测试文字信息");
        
        String driverClassName = CustomizedPropertyPlaceholderConfigurer.getContextProperty("orm.mysql.jdbc.driverClassName");
        String url = CustomizedPropertyPlaceholderConfigurer.getContextProperty("orm.mysql.jdbc.url.v20");
        String username = CustomizedPropertyPlaceholderConfigurer.getContextProperty("orm.mysql.jdbc.username.v20");
        String password = CustomizedPropertyPlaceholderConfigurer.getContextProperty("orm.mysql.jdbc.password.v20");
        mav.addObject("message", "数据库信息");
        mav.addObject("driverClassName", driverClassName);
        mav.addObject("url", url);
        mav.addObject("user", username);
        mav.addObject("password", password);
        
        Map<String, String> map = CustomizedPropertyPlaceholderConfigurer.ctxPropertiesMap;
        for ( String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key+"====>>>>"+value);
        }
        
        return mav;
    }
}
