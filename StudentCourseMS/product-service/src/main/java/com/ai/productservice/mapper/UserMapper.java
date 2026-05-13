package com.ai.productservice.mapper;

import com.ai.productservice.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Select("Select * from user_auth where stuId = #{stuId}")
    User findUserByStuId(Long stuId);

    @Insert("insert into user_auth (stuId,password) values(#{stuId},'123456') ")
    boolean newUser(Long stuId);

    @Select("Select * from user_auth where stuId = #{stuId} and password = #{password}")
    User findBySIAndPW(User user);

    @Update("update user_auth set password = #{password} where stuId = #{stuId}")
    boolean updatePassword(User user);
}
