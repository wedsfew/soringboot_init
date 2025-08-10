package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * 用于存储用户基本信息
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;          // 用户ID
    private String username;   // 用户名
    private String password;   // 密码
    private String email;      // 邮箱
    private String phone;      // 手机号
    private Integer status;    // 状态：0-禁用，1-启用
    private Date createTime;   // 创建时间
    private Date updateTime;   // 更新时间

    /**
     * 默认构造函数
     */
    public User() {
    }

    /**
     * 带参数构造函数
     * 
     * @param id 用户ID
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱
     * @param phone 手机号
     * @param status 状态
     */
    public User(Long id, String username, String password, String email, String phone, Integer status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    // Getter和Setter方法

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}