package com.distributed.roomconsumer.config.MQConfig;

import com.alibaba.fastjson.JSON;
import com.distributed.roomapi.model.KafkaTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    //public static final String TOPIC_MESSAGE = "TOPIC.MESSAGE";

    public void sendMessageTopic(Object obj) {
        String obj2String = JSON.toJSONString(obj);
        logger.info("Ready to send a message as:" + obj2String);
        //send message
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(KafkaTopic.TOPIC_MESSAGE, obj2String);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                //failure
                logger.info(KafkaTopic.TOPIC_MESSAGE + " - producer failed send message:" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                logger.info(KafkaTopic.TOPIC_MESSAGE + " - producer success send message:" + stringObjectSendResult.toString());
            }
        });
    }


    public void clearUnReadMessageTopic(Object obj) {
        String obj2String = JSON.toJSONString(obj);
        logger.info("Ready to clear messages as:" + obj2String);
        //send message
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(KafkaTopic.TOPIC_CLEAR, obj2String);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                //failure
                logger.info(KafkaTopic.TOPIC_MESSAGE + " - producer failed send message:" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                logger.info(KafkaTopic.TOPIC_MESSAGE + " - producer success send message:" + stringObjectSendResult.toString());
            }
        });
    }

}
