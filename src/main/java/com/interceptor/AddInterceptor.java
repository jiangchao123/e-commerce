package com.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by sa on 2017-02-05.
 */
@Configuration
public class AddInterceptor extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry){
        //注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(new LoginInterceptor());
        //配置拦截路径
        ir.addPathPatterns("/**");
        //配置不拦截路径
        ir.excludePathPatterns("/login");
    }
}
