package com.niit.adminservice.config;


import com.niit.adminservice.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-06-30)
 */

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterFilterRegistration(JwtFilter jwtFilter){
        FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(jwtFilter);
        registration.addUrlPatterns("/**");
        registration.setOrder(1);
        return registration;
    }

}
