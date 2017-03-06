package cn.ac.bigo;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * Created by zwb on 2017/2/23.
 */
@SpringBootApplication
@MapperScan("cn.ac.bigo.**.mapper")
public class MainServer extends SpringBootServletInitializer {

    @Value("${custom.resource-base}")
    private String resourceBase;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MainServer.class);
    }

    /**
     * 使用jetty容器启动
     *
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
        factory.addServerCustomizers(new JettyServerCustomizer() {
            @Override
            public void customize(Server server) {
                for (Handler handler : server.getHandlers()) {
                    if (handler instanceof WebAppContext) {
                        WebAppContext webAppContext = (WebAppContext) handler;
                        webAppContext.setResourceBase(resourceBase);
                        server.setHandler(webAppContext);
                    }
                }
            }
        });
        return factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainServer.class, args);
    }
}
