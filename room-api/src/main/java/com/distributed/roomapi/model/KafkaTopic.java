package com.distributed.roomapi.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class KafkaTopic implements Serializable {

    private static final long serialVersionUID = -7670160619837224918L;

    public static final String TOPIC_MESSAGE = "TOPIC.MESSAGE";

    public static final String TOPIC_CLEAR = "TOPIC.CLEAR.MESSAGE";


}
