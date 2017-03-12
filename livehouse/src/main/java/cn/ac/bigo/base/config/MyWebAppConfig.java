package cn.ac.bigo.base.config;

import cn.ac.bigo.backend.interceptor.CheckPrivilegeInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zwb on 2017/3/12.
 */
@Configuration
public class MyWebAppConfig extends WebMvcConfigurerAdapter {

    @Value("${custom.includeUri}")
    private String includeUri;

    /**
     * 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        CheckPrivilegeInterceptor cpi = new CheckPrivilegeInterceptor();
        registry.addInterceptor(cpi).addPathPatterns(includeUri.split(","));//权限拦截
    }
}
