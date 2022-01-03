package com.distributed.roomprovider.mapper;

import com.distributed.roomapi.model.Profile;
import org.apache.ibatis.annotations.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProfileDAO {

    String TABLE_NAME = " profile ";

    String INSERT_NAME = "createDate, userId, nickName, bio, headUrl, location, job";

    String SELECT_NAME = " id, " + INSERT_NAME;

    @Insert({"insert into", TABLE_NAME, " ( ", INSERT_NAME,
            " ) values (#{createDate}, #{userId}, #{nickName},#{bio},#{headUrl},#{location},#{job})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer addProfile(Profile profile);

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where userId = #{userId}"})
    Profile selectByUserId(@Param("userId") Integer userId);

    @Update({"update ", TABLE_NAME,
            "set nickName = #{nickName}, bio = #{bio}, location=#{location}, job = #{job} where id = #{id}"})
    void uodateProfile(Profile profile);

    @Update({"update ", TABLE_NAME, " set headUrl = #{headUrl} where userId = #{userId}"})
    void updateHeadUrl(@Param("headUrl") String headUrl, @Param("userId") Integer userId);

}
