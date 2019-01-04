package com.shangpin.configs;

import com.shangpin.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/12/24 10:43
 * @Description:
 */
@Configuration
public class WebConfigureAdapter extends WebMvcConfigurerAdapter {
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/page/login/**","/user/**", "/static/local-commons/**");
    }
   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
   /*public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){

   }*/
   /*@Bean(name="exceptionResolver")
   public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
   }*/
}
