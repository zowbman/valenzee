package cn.ac.bigo.base.converter;


import cn.ac.bigo.base.util.ConcurrentDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by zwb on 2017/3/6.
 */
public class CustomDateConvert implements Converter<String, Date> {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Date convert(String dateStr) {
        log.info("convert param,dateStr:[{}]", dateStr);
        try {
            return ConcurrentDateUtil.parse(dateStr);
        } catch (ParseException e) {
            log.error("CustomDateConvert error,catch", e);
        }
        return null;
    }
}
