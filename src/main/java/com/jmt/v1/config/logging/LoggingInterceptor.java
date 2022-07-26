package com.jmt.v1.config.logging;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("\n" +
                        "request : {} - {}\n" +
                        "User : {}",
                request.getRequestURI(),
                getIp(request),
                request.getUserPrincipal());
        return super.preHandle(request, response, handler);
    }

    private String getIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null){
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}