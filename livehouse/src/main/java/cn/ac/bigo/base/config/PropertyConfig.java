package cn.ac.bigo.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by zwb on 2016/12/5.
 */
@Configuration
public class PropertyConfig {
    @Bean
    public CustomPropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        CustomPropertyPlaceholderConfigurer cppc = new CustomPropertyPlaceholderConfigurer();
        cppc.setLocations(new Resource[]{new ClassPathResource("application.properties"), new ClassPathResource("code.properties")});
        return cppc;
    }
}
