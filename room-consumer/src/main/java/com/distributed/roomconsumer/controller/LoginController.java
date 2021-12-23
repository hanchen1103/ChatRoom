package com.distributed.roomconsumer.controller;

import com.distributed.roomconsumer.Service.impl.userImpl.UserLoginByAccoountServiceImpl;
import com.distributed.roomconsumer.responsebody.LoginSessionResponseBody;
import com.distributed.roomconsumer.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    UserLoginByAccoountServiceImpl userRespo;

    @PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    public String Login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            HttpSession session = httpServletRequest.getSession();
            String account = httpServletRequest.getParameter("account");
            String password = httpServletRequest.getParameter("password");
            Long expireTime = Long.parseLong(httpServletRequest.getParameter("expireTime"));
            LoginSessionResponseBody res = userRespo.login(account, password, expireTime);
            session.setAttribute("USERKEY", res.getToken());
            return jsonUtil.getJSONString(200, res);
        } catch (NullPointerException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, e.getMessage());
        }
    }

    @PostMapping(value = "/register", produces = {"application/json;charset=UTF-8"})
    public String register(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            HttpSession session = httpServletRequest.getSession();
            String account = httpServletRequest.getParameter("account");
            String password = httpServletRequest.getParameter("password");
            Long expireTime = Long.parseLong(httpServletRequest.getParameter("expireTime"));
            LoginSessionResponseBody res = ((UserLoginByAccoountServiceImpl) userRespo).register(account, password, expireTime);
            session.setAttribute("USERKEY", res.getToken());
            return jsonUtil.getJSONString(200, res);
        } catch (NullPointerException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, e.getMessage());
        }
    }

    @DeleteMapping(value = "/logout", produces = {"application/json;charset=UTF-8"})
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            Integer userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
            ((UserLoginByAccoountServiceImpl) userRespo).logout(userId);
            return jsonUtil.getJSONString(200);
        } catch (NullPointerException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, e.getMessage());
        }
    }
}
