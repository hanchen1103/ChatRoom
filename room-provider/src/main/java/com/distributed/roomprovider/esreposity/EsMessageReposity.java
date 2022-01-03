package com.distributed.roomprovider.esreposity;

import com.distributed.roomapi.model.Message;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EsMessageReposity extends ElasticsearchRepository<Message, String> {

    @Query("{\"match\": {\"content\": {\"query\": \"?0\"}}}")
    List<Message> findByContent(Integer userId, String content, PageRequest pageRequest);
}
