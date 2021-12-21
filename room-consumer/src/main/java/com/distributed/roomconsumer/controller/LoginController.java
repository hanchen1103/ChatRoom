package com.distributed.roomconsumer.controller;

import com.distributed.roomapi.model.User;
import com.distributed.roomconsumer.Service.impl.userImpl.UserLoginByAccoountServiceImpl;
import com.distributed.roomconsumer.Service.respoisty.UserRespo;
import com.distributed.roomconsumer.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    UserRespo userRespo;

    @PostMapping(value = "/login", produces = {"application/json;charset=UTF-8"})
    public String Login(@RequestBody Map<String, String> map) {
        try {
            String account = map.get("account");
            String password = map.get("password");
            User user = ((UserLoginByAccoountServiceImpl) userRespo).login(account, password);
            return jsonUtil.getJSONString(200, user);
        } catch (NullPointerException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, e.getMessage());
        }
    }

    @PostMapping(value = "/register", produces = {"application/json;charset=UTF-8"})
    public String register(@RequestBody Map<String, String> map) {
        try {
            String account = map.get("account");
            String password = map.get("password");
            User user = ((UserLoginByAccoountServiceImpl) userRespo).register(account, password);
            return jsonUtil.getJSONString(200, user);
        } catch (NullPointerException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, e.getMessage());
        }
    }
}
