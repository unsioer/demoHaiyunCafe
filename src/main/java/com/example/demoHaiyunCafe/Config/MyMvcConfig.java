package com.example.demoHaiyunCafe.Config;

import com.example.demoHaiyunCafe.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //所有的WebMvcConfigurer组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer configurer = new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/regist","/regist.html","/login","/login.html","/",
                        "/img/**","/css/**","/js/**","/webjars/**");
                WebMvcConfigurer.super.addInterceptors(registry);
            }
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/regist.html").setViewName("regist");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/checkout.html").setViewName("checkout");
            }
        };

        return configurer;
    }

}