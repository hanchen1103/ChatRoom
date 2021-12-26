package com.distributed.roomconsumer.config.MQConfig;

import com.distributed.roomapi.service.UserService;
import com.distributed.roomconsumer.Service.respoisty.MessageRespo;
import com.distributed.roomconsumer.Service.respoisty.ProfileRespoisty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class KafkaConsumer {

    @Resource
    ProfileRespoisty profileRespoisty;

    @Resource
    MessageRespo messageRespo;


}
