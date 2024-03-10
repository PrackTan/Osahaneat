//package com.cybersoft.osahaneat.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Collections;
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class CORSConfig extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
//        response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
//        if ("OPTIONS".equals(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            filterChain.doFilter(request, response);
//        }
//    }
//    }
////    @Bean
////    public CorsFilter corsFilter() {
////        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowedOrigins(Collections.singletonList("http://localhost:5500"));
////        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
////        config.setAllowedHeaders(Collections.singletonList("*"));
//////        config.setAllowCredentials(true);
//////        return new CorsFilter(config);
//////    }
////    @Bean
////    public WebMvcConfigurer getCORSConfiguation(){
////        return new WebMvcConfigurer() {
////            @Override
////            public void addCorsMappings(CorsRegistry registry) {
////                registry.addMapping("/**")
////                        .allowedOrigins("*")
////                        .allowedMethods("GET","POST","PUT","DELETE")
////                        .allowedHeaders("*");
////
////            }
////        };
////    }
//
