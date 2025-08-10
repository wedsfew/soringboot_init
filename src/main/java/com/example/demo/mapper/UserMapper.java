package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 用户Mapper接口
 * 提供用户相关的数据库操作方法
 */
@Mapper
public interface UserMapper {

    /**
     * 根据ID查询用户
     * 
     * @param id 用户ID
     * @return 用户对象
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Long id);

    /**
     * 根据用户名查询用户
     * 
     * @param username 用户名
     * @return 用户对象
     */
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    /**
     * 查询所有用户
     * 
     * @return 用户列表
     */
    @Select("SELECT * FROM user")
    List<User> findAll();

    /**
     * 插入新用户
     * 
     * @param user 用户对象
     * @return 影响的行数
     */
    @Insert("INSERT INTO user(username, password, email, phone, status, create_time, update_time) " +
            "VALUES(#{username}, #{password}, #{email}, #{phone}, #{status}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    /**
     * 更新用户信息
     * 
     * @param user 用户对象
     * @return 影响的行数
     */
    @Update("UPDATE user SET username = #{username}, password = #{password}, " +
            "email = #{email}, phone = #{phone}, status = #{status}, update_time = #{updateTime} " +
            "WHERE id = #{id}")
    int update(User user);

    /**
     * 根据ID删除用户
     * 
     * @param id 用户ID
     * @return 影响的行数
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteById(Long id);
}