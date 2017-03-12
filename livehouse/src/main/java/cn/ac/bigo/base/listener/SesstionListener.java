package cn.ac.bigo.base.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zwb on 2017/3/12.
 */
public class SesstionListener implements HttpSessionListener {
    private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        //session创建
        sessions.put(sessionEvent.getSession().getId(), sessionEvent.getSession());
    }

    //session销毁
    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        //当session摧毁从map对象删除
        sessions.remove(sessionEvent.getSession().getId());
    }

    /**
     * 获取session
     *
     * @param sessionId
     * @return
     */
    public static HttpSession getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    /**
     * 删除cookie中的sessionid
     *
     * @param sessionID
     */
    public static void removeSession(String sessionID) {
        if (sessions.containsKey(sessionID))
            sessions.remove(sessionID);
    }
}
