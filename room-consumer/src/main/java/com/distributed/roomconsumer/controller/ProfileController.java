package com.distributed.roomconsumer.controller;

import com.distributed.roomapi.model.Profile;
import com.distributed.roomconsumer.Service.resposity.ProfileRespoisty;
import com.distributed.roomconsumer.Service.resposity.UserQueryResposity;
import com.distributed.roomconsumer.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping(value = "/{userId}", produces = "application/json;charset=UTF-8")
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

    @PutMapping(value = "", produces = "application/json;charset=UTF-8")
    public String updateProfileByUserId(HttpServletRequest httpServletRequest) {
        try {
            Profile profile = new Profile();
            profile.setUserId(Integer.parseInt(httpServletRequest.getParameter("userId")));
            profile.setNickName(httpServletRequest.getParameter("nickName"));
            return jsonUtil.getJSONString(200);
        } catch (NullPointerException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500);
        }
    }
}
