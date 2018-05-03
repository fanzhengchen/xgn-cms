package com.xgn.cms.mapper;

import com.xgn.cms.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("userName") String userName);

    int insert(User record);

    User selectByPrimaryKey(@Param("userId") Integer userId, @Param("userName") String userName);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}