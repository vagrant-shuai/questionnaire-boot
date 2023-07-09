package com.xs.api.config;

import com.xs.api.web.filter.HttpTraceLogFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import javax.servlet.DispatcherType;

/**
 * @author xs
 * @description 过滤器配置
 **/
@Configuration
@RequiredArgsConstructor
public class FilterConfig {


    /**
     * 跨域过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);
        CorsFilter corsFilter = new CorsFilter(corsConfigurationSource);
        registration.setOrder(Integer.MAX_VALUE - 4);
        registration.setFilter(corsFilter);
        return registration;
    }



    /**
     * 请求日志
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "platform.request", name = "trace-log", havingValue = "true")
    public FilterRegistrationBean httpTraceLogFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new HttpTraceLogFilter());
        registration.addUrlPatterns("/*");
        registration.setName("httpTraceLogFilter");
        registration.setOrder(Integer.MAX_VALUE - 2);
        return registration;
    }

}
