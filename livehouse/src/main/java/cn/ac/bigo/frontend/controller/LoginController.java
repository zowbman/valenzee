package cn.ac.bigo.frontend.controller;

import cn.ac.bigo.base.helper.CodeHelper;
import cn.ac.bigo.base.model.po.PubReturnMsg;
import cn.ac.bigo.base.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zwb on 2017/3/12.
 */
@Controller
@RequestMapping("/frontend/json")
public class LoginController {

    private Logger log = LoggerFactory.getLogger(getClass());

    private static String PWD = "valenzee2017";

    /**
     * 登陆/注销
     *
     * @param pwd
     * @return
     */
    @PostMapping("/v1/login")
    @ResponseBody
    public PubReturnMsg login(String pwd, String referrerUrl, HttpSession httpSession, @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId, HttpServletResponse resp) {
        log.info("param pwd:[{}],referrerUrl:[{}]", pwd, referrerUrl);
        if (pwd != null && !"".equals(pwd) && PWD.equals(pwd)) {
            //存session
            httpSession.setAttribute("loginStatus", true);
            WebUtil.addCookie(resp, "loginStatus", sessionId, 30 * 60);
            return new PubReturnMsg(CodeHelper.SUCCESS, "登陆成功", referrerUrl);
        } else if (pwd != null && !"".equals(pwd) && "quit".equals(pwd.toLowerCase())) {
            httpSession.removeAttribute("loginStatus");
            return new PubReturnMsg(CodeHelper.SUCCESS, "注销成功", referrerUrl);
        }
        return new PubReturnMsg(CodeHelper.PWDERROR);
    }
}
