package com.distributed.roomprovider.mapper;

import com.distributed.roomapi.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDAO {
    String TABLE_NAME = " user ";

    String INSERT_NAME = " account, password, salt, status";

    String SELECT_NAME = "id, " + INSERT_NAME;

    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where id = #{id}"})
    User selectByuserId(@Param("id") Integer id);


    @Select({"select ", SELECT_NAME, " from ", TABLE_NAME, " where account = #{account}"})
    User selectByUserAccount(@Param("account") String account);

    @Insert({"insert into ", TABLE_NAME,
            " ( ", INSERT_NAME, " ) values (#{account}, #{password}, #{salt}, #{status})"})
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer addUser(User user);

    @Select({"select count(id) from", TABLE_NAME, "where account = #{account}"})
    Integer isExist(String account);

    @Update({"update ", TABLE_NAME, " set password = #{password} where id = #{id}"})
    Integer updatePassword(String password, Integer id);
}
