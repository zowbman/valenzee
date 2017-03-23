package cn.ac.bigo.base.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by zwb on 2017/1/6.ObjectMapper 单例
 */
public class JacksonMapper {

    private static final ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private JacksonMapper(){}

    public static ObjectMapper getInstance() {
        return mapper;
    }

    public static String getJsonString(Object object) throws Exception {
        return JacksonMapper.getInstance().writeValueAsString(object);
    }
}
