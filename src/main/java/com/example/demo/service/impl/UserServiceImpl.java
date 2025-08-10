package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户服务实现类
 * 实现用户相关的业务逻辑
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据ID查询用户
     * 
     * @param id 用户ID
     * @return 用户对象
     */
    @Override
    public User getUserById(Long id) {
        return userMapper.findById(id);
    }

    /**
     * 根据用户名查询用户
     * 
     * @param username 用户名
     * @return 用户对象
     */
    @Override
    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     * 查询所有用户
     * 
     * @return 用户列表
     */
    @Override
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    /**
     * 创建新用户
     * 创建前会对密码进行加密处理
     * 
     * @param user 用户对象
     * @return 创建的用户对象
     */
    @Override
    public User createUser(User user) {
        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 设置状态为启用
        user.setStatus(1);
        // 设置创建和更新时间
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        
        userMapper.insert(user);
        return user;
    }

    /**
     * 更新用户信息
     * 如果密码不为空，则对密码进行加密处理
     * 
     * @param user 用户对象
     * @return 更新后的用户对象
     */
    @Override
    public User updateUser(User user) {
        try {
            System.out.println("开始更新用户: " + user.getId());
            
            User existingUser = userMapper.findById(user.getId());
            if (existingUser == null) {
                System.out.println("用户不存在: " + user.getId());
                return null;
            }
            
            // 如果密码不为空，则进行加密
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                // 否则保持原密码不变
                user.setPassword(existingUser.getPassword());
            }
            
            // 确保状态字段不为空
            if (user.getStatus() == null) {
                user.setStatus(existingUser.getStatus());
            }
            
            // 更新时间
            user.setUpdateTime(new Date());
            // 保持创建时间不变
            user.setCreateTime(existingUser.getCreateTime());
            
            System.out.println("更新用户信息: " + user.getUsername() + ", 状态: " + user.getStatus());
            int result = userMapper.update(user);
            System.out.println("更新结果: " + result);
            
            return user;
        } catch (Exception e) {
            System.err.println("更新用户异常: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 删除用户
     * 
     * @param id 用户ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteUser(Long id) {
        return userMapper.deleteById(id) > 0;
    }
}