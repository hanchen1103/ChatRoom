package com.distributed.roomconsumer.controller;

import com.distributed.roomconsumer.Service.resposity.ProfileRespoisty;
import com.distributed.roomconsumer.Service.resposity.UserQueryResposity;
import com.distributed.roomconsumer.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Resource
    ProfileRespoisty profileRespoisty;

    @Resource
    UserQueryResposity userQueryResposity;

    @GetMapping(value = "/user/{userId}", produces = "application/json;charset=UTF-8")
    public String selectProfileByUserId(@PathVariable Integer userId) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("user", userQueryResposity.selectUserById(userId));
            map.put("profile", profileRespoisty.selectByUserId(userId));
            return jsonUtil.getJSONString(200, map);
        } catch (NullPointerException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500);
        }
    }
}
