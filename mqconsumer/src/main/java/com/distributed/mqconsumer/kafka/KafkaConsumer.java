package com.distributed.mqconsumer.kafka;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.distributed.roomapi.model.KafkaTopic;
import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.service.MessageService;
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
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Reference
    MessageService messageService;


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

    @KafkaListener(topics = KafkaTopic.TOPIC_CLEAR, groupId = KafkaTopic.TOPIC_MESSAGE)
    public void consumeClearMessage(ConsumerRecord<?, ?> record, Acknowledgment ack,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            String msg = String.valueOf(message.get());
            JSONObject jsonObject = JSON.parseObject(msg);
            messageService.clearUnReadMessage(jsonObject.getInteger("userId"));
            logger.info(KafkaTopic.TOPIC_MESSAGE + " consumed： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }
}
