package cn.ac.bigo.base.cache;

import cn.ac.bigo.base.util.ConcurrentDateUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zwb on 2017/2/28.
 */
@Component
public class ApplyDateListCache implements InitializingBean {
    private Logger log = LoggerFactory.getLogger(getClass());
    private LoadingCache<String, List<String>> applyDateListCache;
    private int maximumSize = 10; //default 10
    private int expireTime = 86400;//default 1 day

    @Override
    public void afterPropertiesSet() throws Exception {
        applyDateListCache = CacheBuilder.newBuilder().maximumSize(maximumSize)
                .expireAfterAccess(expireTime, TimeUnit.SECONDS).build(new CacheLoader<String, List<String>>() {
                    @Override
                    public List<String> load(String key) throws Exception {
                        List<String> applyDateList = new ArrayList<String>();
                        Calendar c = Calendar.getInstance();
                        c.setTime(ConcurrentDateUtil.parse(key));
                        applyDateList.add(ConcurrentDateUtil.format(c.getTime()));
                        c.add(Calendar.DAY_OF_MONTH, +1);
                        applyDateList.add(ConcurrentDateUtil.format(c.getTime()));
                        c.add(Calendar.DAY_OF_MONTH, +1);
                        applyDateList.add(ConcurrentDateUtil.format(c.getTime()));
                        log.info("重新缓存applyDateList:[{}]", applyDateList.toString());
                        return applyDateList;
                    }
                });
    }

    public List<String> getApplyDateList() {
        List<String> applyDateList = new ArrayList<>();
        try {
            applyDateList = applyDateListCache.get(ConcurrentDateUtil.format(System.currentTimeMillis()));
            return applyDateList;
        } catch (ExecutionException e) {
            log.error("catch ", e);
        }
        return applyDateList;
    }
}
