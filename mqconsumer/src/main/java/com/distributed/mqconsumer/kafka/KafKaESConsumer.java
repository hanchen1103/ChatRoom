package com.distributed.mqconsumer.kafka;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.distributed.roomapi.model.KafkaTopic;
import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.service.MessageEsService;
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
public class KafKaESConsumer {

    @Reference
    MessageEsService messageEsService;

    private static final Logger logger = LoggerFactory.getLogger(KafkaDBConsumer.class);

    @KafkaListener(topics = KafkaTopic.TOPIC_ES_MESSAGE, groupId = KafkaTopic.TOPIC_ES_MESSAGE)
    public void consumeSendMessage(ConsumerRecord<?, ?> record, Acknowledgment ack,
                                   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        logger.info(message.toString());
        if (message.isPresent()) {
            String msg = String.valueOf(message.get());
            Message sentMessage = JSON.parseObject(msg, Message.class);
            messageEsService.save2Es(sentMessage);
            logger.info(KafkaTopic.TOPIC_ES_MESSAGE + " consumed： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }
}
