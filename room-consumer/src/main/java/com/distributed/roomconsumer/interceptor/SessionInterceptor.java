package com.distributed.roomconsumer.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.distributed.roomapi.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Reference
    SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("USERKEY") == null ||
                request.getParameter("userId") == null) {
            logger.error("session params exception");
            response.setHeader("statuscode", "305");
            response.setHeader("set-cookie", "xxxxx");
            return false;
        }
        String token = (String) request.getSession().getAttribute("USERKEY");
        String userId = request.getParameter("userId");
        return sessionService.getSessionValue(userId).equals(token);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
