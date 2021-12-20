package com.distributed.roomprovider.mapper;

import com.distributed.roomapi.model.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MessageDAO {

    String TABLE_NAME = " message ";

    String INSERT_NAME = " content, type, createDate, fromId, toId, isRead";

    String SELECT_NAME = " id, " + INSERT_NAME;

    @Insert({"insert into ", TABLE_NAME, " ( ", INSERT_NAME,
            " ) values (#{content},#{type},#{createDate},#{fromId},#{toId},#{isRead})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer addMessage(Message message);

    @Select({"select ", SELECT_NAME, " from " ,TABLE_NAME, " where id = #{id}"})
    Message selectById(@Param("Integer") Integer id);

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME,
            " where fromId=#{fromId} and toId=#{toId} order by id desc limit #{limit}, #{offset}"})
    List<Message> selectMessageListByFromIdAndtoId(Integer fromId, Integer toId,
                                                   Integer limit, Integer offset);
}
