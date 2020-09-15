package com.bainianzhi.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {
    @Bean
    public RedisSessionInterceptor getSessionInterceptor() {
        return new RedisSessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSessionInterceptor())
                .addPathPatterns("rain/luckdraw/**")
                .addPathPatterns("rain/user/**")
                .addPathPatterns("rain/viewuserhit/**");
        super.addInterceptors(registry);
    }
}
