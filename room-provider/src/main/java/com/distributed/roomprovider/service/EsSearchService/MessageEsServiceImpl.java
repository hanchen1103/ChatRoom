package com.distributed.roomprovider.service.EsSearchService;


import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.service.MessageEsService;
import com.distributed.roomprovider.esreposity.EsMessageReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class MessageEsServiceImpl implements MessageEsService {

    @Autowired
    EsMessageReposity esMessageReposity;

    @Override
    public void save2Es(Message message) {
        esMessageReposity.save(message);
    }

    @Override
    public List<Message> queryMessageByFromIdAnd2IdAndContent(Integer fromId, Integer toId, String content) {
        return null;
    }
}
