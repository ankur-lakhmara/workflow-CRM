package org.developerport.workflowcrmbackend.config;

import jakarta.servlet.FilterRegistration;
import lombok.RequiredArgsConstructor;
import org.developerport.workflowcrmbackend.config.auth.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    private final JwtFilter jwtFilter;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterBean(){
        FilterRegistrationBean<JwtFilter> bean  = new FilterRegistrationBean<>();
        bean.setFilter(jwtFilter);
        bean.addUrlPatterns("/*");
        return bean;
    }
}
