package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

/**
 * 用户服务接口
 * 定义用户相关的业务操作
 */
public interface UserService {

    /**
     * 根据ID查询用户
     * 
     * @param id 用户ID
     * @return 用户对象
     */
    User getUserById(Long id);

    /**
     * 根据用户名查询用户
     * 
     * @param username 用户名
     * @return 用户对象
     */
    User getUserByUsername(String username);

    /**
     * 查询所有用户
     * 
     * @return 用户列表
     */
    List<User> getAllUsers();

    /**
     * 创建新用户
     * 
     * @param user 用户对象
     * @return 创建的用户对象
     */
    User createUser(User user);

    /**
     * 更新用户信息
     * 
     * @param user 用户对象
     * @return 更新后的用户对象
     */
    User updateUser(User user);

    /**
     * 删除用户
     * 
     * @param id 用户ID
     * @return 是否删除成功
     */
    boolean deleteUser(Long id);
}