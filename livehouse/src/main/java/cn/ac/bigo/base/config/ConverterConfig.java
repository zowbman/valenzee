package cn.ac.bigo.base.config;

import cn.ac.bigo.base.converter.CustomDateConvert;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zwb on 2017/3/6.
 */
@Configuration
public class ConverterConfig {
    @Bean
    public CustomDateConvert customDateConvert() {
        return new CustomDateConvert();
    }
}
