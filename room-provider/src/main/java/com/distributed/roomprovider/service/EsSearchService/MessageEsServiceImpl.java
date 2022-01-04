package com.distributed.roomprovider.service.EsSearchService;


import com.distributed.roomapi.model.Message;
import com.distributed.roomapi.service.MessageEsService;
import com.distributed.roomprovider.esreposity.EsMessageReposity;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@com.alibaba.dubbo.config.annotation.Service
public class MessageEsServiceImpl implements MessageEsService {

    @Autowired
    EsMessageReposity esMessageReposity;

    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public void save2Es(Message message) {
        esMessageReposity.save(message);
    }

    @Override
    public List<Message> queryMessageByFromIdAnd2IdAndContent(Integer fromId, Integer toId, String content) {
//        return esMessageReposity.findByContentAndFromIdAnd2Id(String.valueOf(fromId),
//                String.valueOf(toId), content);
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        TermQueryBuilder termQueryBuilder1 = QueryBuilders.termQuery("fromId", fromId);
//        boolQueryBuilder.must(termQueryBuilder1);
//        TermQueryBuilder termQueryBuilder2 = QueryBuilders.termQuery("toId", toId);
//        boolQueryBuilder.must(termQueryBuilder2);
//        TermQueryBuilder termQueryBuilder3 = QueryBuilders.termQuery("content", content);
//        boolQueryBuilder.should(termQueryBuilder3);
//        SortBuilder<?> sortBuilder = SortBuilders.fieldSort("createDate").order(SortOrder.DESC);
//        queryBuilder.withSort(sortBuilder);
//        queryBuilder.withQuery(boolQueryBuilder).build();
//        SearchHits<Message> messageHits =
//                elasticsearchOperations.search(queryBuilder, Message.class, IndexCoordinates.of("message_index"));
        return null;
    }
}
