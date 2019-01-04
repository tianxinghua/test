package com.shangpin.configs;

import java.io.File;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
/*import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/11/26 16:17
 * @Description:
 */
@Configuration
public class TomcatConfig {
  /*  @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        ConfigurableEmbeddedServletContainer factory = new TomcatEmbeddedServletContainerFactory();
        factory.setDocumentRoot(new File("D:\\Study\\learn-springboot\\module1\\src\\main\\webapp\\"));
        return (EmbeddedServletContainerFactory) factory;
    }*/

}
