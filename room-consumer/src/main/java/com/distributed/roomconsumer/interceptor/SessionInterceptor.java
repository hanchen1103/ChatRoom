package com.distributed.roomconsumer.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.distributed.roomapi.service.SessionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SessionInterceptor implements HandlerInterceptor {

    @Reference
    SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("USERKEY") == null ||
                request.getParameter("userId") == null) {
            return false;
        }
        String token = (String) request.getSession().getAttribute("USERKEY");
        String userId = request.getParameter("userId");
        return sessionService.getSessionValue(userId).equals(token);
    }
}
