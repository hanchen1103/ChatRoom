package com.distributed.roomconsumer.controller;

import com.distributed.roomconsumer.Service.resposity.EsQueryResposity;
import com.distributed.roomconsumer.Service.resposity.MessageResposity;
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
    MessageResposity messageRespo;


    @PostMapping(value = "/send", produces = "application/json;charset=UTF-8")
    public String sendMsg(HttpServletRequest httpServletRequest) {
        try {
            String content = httpServletRequest.getParameter("content");
            Integer fromId = Integer.parseInt(httpServletRequest.getParameter("fromId"));
            Integer toId = Integer.parseInt(httpServletRequest.getParameter("toId"));
            Integer type = Integer.parseInt(httpServletRequest.getParameter("type"));
            Integer isRead = Integer.parseInt(httpServletRequest.getParameter("isRead"));
            messageRespo.sendMessage(content, type, fromId, toId, isRead);
            return jsonUtil.getJSONString(200);
        } catch (NullPointerException | NumberFormatException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, e.getMessage());
        }
    }

    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public String getProfile(Integer userId, Integer limit, Integer offset) {
        try {
            return jsonUtil.getJSONString(200,
                    messageRespo.selectContactByFromIdAnd2Id(userId, limit, offset));
        } catch (NullPointerException | NumberFormatException e) {
            logger.error(e.getMessage());
            return jsonUtil.getJSONString(500, e.getMessage());
        }
    }

//    @GetMapping(value = "/search", produces = "application/json;charset=UTF-8")
//    public String searchMessage(HttpServletRequest httpServletRequest) {
//        try {
//            return jsonUtil.getJSONString(200,
//                    esQueryResposity.queryMessage(Integer.parseInt(httpServletRequest.getParameter("fromId")),
//                            Integer.parseInt(httpServletRequest.getParameter("toId")), httpServletRequest.getParameter("q")))   ;
//        } catch (NullPointerException e) {
//            logger.error(e.getMessage());
//            return jsonUtil.getJSONString(500, e.getMessage());
//        }
//    }
}
