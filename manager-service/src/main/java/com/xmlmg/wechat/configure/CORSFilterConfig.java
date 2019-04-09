package com.xmlmg.wechat.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 服务端跨域访问控制Filter
 */

@Configuration
public class CORSFilterConfig  {
    @Value("${cors.service.url}")
    private String url;
    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CORSAFilter(this.url));
        registration.addUrlPatterns("*");
        registration.setName("corsFilter");
        registration.setOrder(1);
        return registration;
    }
}
class CORSAFilter implements  Filter{
    private String url;
    public CORSAFilter(String url){
        this.url=url;
    }
    @Override
    public void init(FilterConfig filterConfig) {
        //
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String origin = ((HttpServletRequest)servletRequest).getHeader("origin");
        String referer = ((HttpServletRequest)servletRequest).getHeader("referer");

        if(origin == null) {
            origin = referer;
        }

        String allowedOrigin;
        if(origin == null || !origin.matches(this.url)) {
            allowedOrigin = this.url;
        } else {
            allowedOrigin = origin;
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", allowedOrigin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, x-token");
        response.setHeader("Access-Control-Request-Headers", "Access-Control-Allow-Credentials，Access-Control-Allow-Origin, Access-Control-Allow-Headers");
        response.setHeader("Access-Control-Expose-Headers", "sessionstatus");
        if(!((HttpServletRequest)servletRequest).getMethod().equalsIgnoreCase("options")){//OPTIONS访问直接响应
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        //
    }
}
