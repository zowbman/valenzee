package cn.ac.bigo.base.cache;

import cn.ac.bigo.base.service.IStartTimeService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zwb on 2017/3/10.
 */
@Component
public class StartTimeCache implements InitializingBean {
    private Logger log = LoggerFactory.getLogger(getClass());
    private LoadingCache<Integer, String> startTime;
    private int maximumSize = 10; //default 10
    private int expireTime = 86400;//default 1 day

    @Autowired
    IStartTimeService iStartTimeService;

    @Override
    public void afterPropertiesSet() throws Exception {
        startTime = CacheBuilder.newBuilder().maximumSize(maximumSize)
                .expireAfterAccess(expireTime, TimeUnit.SECONDS).build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) throws Exception {
                        String startTime;
                        if (key > 48) {
                            startTime = iStartTimeService.getStartTimeById(1);
                        } else {
                            startTime = iStartTimeService.getStartTimeById(key);
                        }

                        return startTime;
                    }
                });
    }

    public String getStartTime(int id) {
        try {
            return startTime.get(id);
        } catch (ExecutionException e) {
            log.error("catch ", e);
        }
        return null;
    }
}
