package com.kinman.mapper;

import com.kinman.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UsersMapper {

    @Delete("delete from users where id = #{id}")
    void deleteUserById(long id);
    /**
     * 条件查询用户列表，根据用户名和身份
     * @param username  用户名
     * @param identity  身份
     * @return  用户列表
     */
    List<User> getUserList(String username, Boolean identity);
    @Select("select * from users where username = #{username} and password = #{password}")
    User getUserByNameAndPassword(User user);
}
