package com.distributed.mqconsumer.kafka;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.distributed.roomapi.model.KafkaTopic;
import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.model.Profile;
import com.distributed.roomapi.service.MessageService;
import com.distributed.roomapi.service.ProfileService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaDBConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaDBConsumer.class);

    @Reference
    MessageService messageService;

    @Reference
    ProfileService profileService;


    @KafkaListener(topics = KafkaTopic.TOPIC_MESSAGE, groupId = KafkaTopic.TOPIC_MESSAGE)
    public void consumeSendMessage(ConsumerRecord<?, ?> record, Acknowledgment ack,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        logger.info(message.toString());
        if (message.isPresent()) {
            String msg = String.valueOf(message.get());
            Message sentMessage = JSON.parseObject(msg, Message.class);
            messageService.addMessage(sentMessage);
            logger.info(KafkaTopic.TOPIC_MESSAGE + " consumed： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaTopic.TOPIC_CLEAR, groupId = KafkaTopic.TOPIC_CLEAR)
    public void consumeClearMessage(ConsumerRecord<?, ?> record, Acknowledgment ack,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            String msg = String.valueOf(message.get());
            messageService.clearUnReadMessage(Integer.parseInt(msg));
            logger.info( KafkaTopic.TOPIC_CLEAR + " consumed： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }


    @KafkaListener(topics = KafkaTopic.TOPIC_PROFILE, groupId = KafkaTopic.TOPIC_PROFILE)
    public void consumeAddProfile(ConsumerRecord<?, ?> record, Acknowledgment ack,
                                    @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            try {
                String msg = String.valueOf(message.get());
                Profile profile = JSON.parseObject(msg, Profile.class);
                profileService.addProfile(profile);
                logger.info(KafkaTopic.TOPIC_PROFILE + " consumed： Topic:" + topic + ",Message:" + msg);
                logger.info(profileService.getProfileByUserId(profile.getUserId()).toString());
                ack.acknowledge();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
