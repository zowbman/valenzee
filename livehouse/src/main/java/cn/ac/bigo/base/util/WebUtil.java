package cn.ac.bigo.base.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zwb on 2017/3/12.
 */
public class WebUtil {
    public WebUtil() {
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }

        response.addCookie(cookie);
    }

    public static String getCookieByName(HttpServletRequest request, String name) {
        Map cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie.getValue();
        } else {
            return null;
        }
    }

    protected static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        HashMap cookieMap = new HashMap();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; ++i) {
                cookieMap.put(cookies[i].getName(), cookies[i]);
            }
        }

        return cookieMap;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        return StringUtils.isBlank(requestType) && "XMLHttpRequest".equalsIgnoreCase(requestType);
    }
}
