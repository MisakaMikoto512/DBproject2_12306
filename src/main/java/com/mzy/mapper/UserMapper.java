package com.mzy.mapper;

import com.mzy.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mzy
 * @since 2020-05-22
 */
@Mapper
public interface UserMapper {
    @Select("select user_id from user where user_id = #{userName}")
    public String selectUserName(String userName);

    @Select("select password from user where user_id = #{userName}")
    public String selectUserPassword(String userName);

    @Select("select authority from user where user_id = #{userName}")
    public String selectUserAuthoity(String userName);

    @Insert("Insert into user(user_id, password,authority,phone_number) values(#{userName},#{userPassword},#{authority},#{phone})")
    public void addUser(String userName, String userPassword,String authority,String phone);
}
