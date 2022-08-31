package com.kaede.database.mapper;

import com.kaede.database.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author kaede
 * @create 2022-08-29 15:49
 */

@Mapper
public interface UserMapper {

    //根据id查询用户信息
    User getUserById(@Param("id") Integer id);

}
