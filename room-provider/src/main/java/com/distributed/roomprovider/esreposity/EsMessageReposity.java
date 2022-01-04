package com.distributed.roomprovider.esreposity;

import com.distributed.roomapi.model.Message;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EsMessageReposity extends ElasticsearchRepository<Message, Integer> {

    @Query("{\n" +
            "    \"query\": {\n" +
            "        \"constant_score\" : {\n" +
            "            \"filter\" : {\n" +
            "                 \"bool\" : {\n" +
            "                    \"must\" : [\n" +
            "                        { \"term\" : { \"fromId\" : \"?0\" } }, \n" +
            "                        { \"term\" : { \"toId\" : \"?1\" } } \n" +
            "                    ],\n" +
            "                    \"should\": [\n" +
            "                        { \"match\": { \"content\": \"?2\" } }\n" +
            "                    ]\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    },\n" +
            "    \"sort\": { \"createDate\": { \"order\": \"desc\" }}\n" +
            "}")
    List<Message> findByContentAndFromIdAnd2Id(String fromId, String toId, String content);
}
