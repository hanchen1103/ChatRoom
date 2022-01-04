package com.distributed.roomconsumer.Service.resposity;

import com.distributed.roomapi.model.Message;

import java.util.List;

public interface EsQueryResposity {

    List<Message> queryMessage(Integer fromId, Integer toId, String q);
}
