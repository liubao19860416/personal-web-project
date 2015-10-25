package validatecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 下载APP页面相关操作操作
 * 
 */
@Controller
@RequestMapping("/bygj")
public class BygjController /* extends BaseController<BygjController> */{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // @Resource
    // private UniMessageService uniMessageService;

    @RequestMapping(value = "/start/0", method = RequestMethod.GET)
    public ModelAndView hello(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("courtName", "a");
        model.put("reservations", "b");
        return new ModelAndView("bygj/bygj", model);
    }

    @RequestMapping("/bygjCheck")
    @ResponseBody
    public Object checkCode(HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws Exception {
        // 获取登陆参数并验证
        String vaco = request.getParameter("checkCode");
        // 验证验证码
        String validCode = (String) session
                .getAttribute(RandomValidateCode.RANDOMCODEKEY);
        if (vaco.toLowerCase().equals(validCode.toLowerCase())) {
            session.removeAttribute(RandomValidateCode.RANDOMCODEKEY);
            return "0";
        }
        return "1";
    }

    @RequestMapping("/sendMessage")
    @ResponseBody
    public Object bygjSendMessage(HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws Exception {
        // 获取登陆参数并验证
        List<String> phoneList = new ArrayList<String>();
        String phoneNo = "+86" + request.getParameter("phoneNo");
        phoneList.add(phoneNo);

        // Sms sms = new Sms(ConsField.UMS_REGISTER_APPID, "009");
        // sms.setDestPhones(phoneList);
        // sms.setParams(new HashMap<String, String>());

        try {
            logger.debug("发送短信开始.....");
            // uniMessageService.sendSms(sms);
            logger.debug("发送短信结束.....");
            return "0";
        } catch (Exception e) {
            logger.error("调用发送短信接口服务失败", e);
        }
        return "2";
    }

}
