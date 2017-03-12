package cn.ac.bigo.backend.common;

import cn.ac.bigo.base.listener.SesstionListener;
import cn.ac.bigo.base.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zwb on 2017/3/12.
 */
public class CurrentLoginStatus {
    /**
     * 获取当前登陆状态
     *
     * @param request
     * @param response
     * @return
     */
    public static boolean get(HttpServletRequest request, HttpServletResponse response) {
        Boolean loginStatus = (Boolean) request.getSession().getAttribute("loginStatus");
        if (loginStatus != null && !loginStatus) {
            String sid = WebUtil.getCookieByName(request, "currentLoginStatus");
            if (sid != null) {
                HttpSession session = SesstionListener.getSession(sid);
                if (session != null) {
                    loginStatus = (Boolean) session.getAttribute("currentLoginStatus");
                    if (loginStatus != null && loginStatus) {
                        SesstionListener.removeSession(sid);
                        request.getSession().setAttribute("currentLoginStatus", loginStatus);
                        WebUtil.addCookie(response, "currentLoginStatus", request.getSession().getId(), 30 * 60);
                    }
                }
            }
        }
        return loginStatus == null ? false : loginStatus;
    }
}
