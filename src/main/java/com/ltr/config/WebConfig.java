package com.ltr.config;

import com.ltr.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer { //配置类

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    /* 拦截除 /login 外所有的请求 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register");
    }

}
