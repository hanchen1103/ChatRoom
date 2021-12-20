package com.distributed.roomconsumer.controller;

import com.distributed.roomapi.service.MessageResposity;
import com.distributed.roomconsumer.Service.respoisty.MessageRespo;
import com.distributed.roomconsumer.util.jsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Resource
    MessageRespo messageRespo;

    @PostMapping(value = "", produces = "application/json;charset=UTF-8")
    public String sendMsg(HttpServletRequest httpServletRequest) {
        try {
            String content = httpServletRequest.getParameter("content");
            Integer fromId = Integer.parseInt(httpServletRequest.getParameter("fromId"));
            Integer toId = Integer.parseInt(httpServletRequest.getParameter("toId"));
            Integer type = Integer.parseInt(httpServletRequest.getParameter("type"));
            Integer isRead = Integer.parseInt(httpServletRequest.getParameter("isRead"));
            messageRespo.sendMessage(content, type, fromId, toId, isRead);
            return jsonUtil.getJSONString(200);
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, e.getMessage());
        }
    }

    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public String getProfile(@RequestBody Map<String, String> map) {
        try {
            return jsonUtil.getJSONString(200,
                    messageRespo.getFromIdAnd2IdProfile(Integer.parseInt(map.get("fromId")),
                            Integer.parseInt(map.get("toId")), Integer.parseInt(map.get("limit")),
                            Integer.parseInt(map.get("offset"))));
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, e.getMessage());
        }
    }
}
