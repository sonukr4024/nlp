//package com.nlp.nlp.config;
//
//import org.springframework.web.context.request.RequestContextHolder;
//
//import javax.servlet.*;
//import java.io.IOException;
//
//public class CustomFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        SessionUtils.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
//
