package com.kaede.database.service;

import com.kaede.database.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author kaede
 * @create 2022-08-29 15:59
 */

public interface UserService {

    //根据id查询用户信息
    User getUserById(@Param("id") Integer id);

}
