package cn.ac.bigo.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zwb on 2016/12/5.
 */
public class BaseUtil {

    private static Logger logger = LoggerFactory.getLogger(BaseUtil.class);

    /**
     * 时间戳10位转13位
     *
     * @param timeMillis
     * @return
     */
    public static long timeMillis13(int timeMillis) {
        return timeMillis * 1000L;
    }

    /**
     * 时间戳13位转10位
     *
     * @param timeMillis
     * @return
     */
    public static int timeMillis10(long timeMillis) {
        return (int) (timeMillis / 1000);
    }

    /**
     * 当前时间戳(10位)
     *
     * @return
     */
    public static int currentTimeMillis() {
        return timeMillis10(System.currentTimeMillis());
    }
}
