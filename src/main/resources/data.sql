-- 使用数据库
USE demo;

-- 初始化用户数据
-- 密码为 'password'，使用BCrypt加密
INSERT INTO `user` (`username`, `password`, `email`, `phone`, `status`, `create_time`, `update_time`)
VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'admin@example.com', '13800000000', 1, NOW(), NOW()),
('user', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'user@example.com', '13900000000', 1, NOW(), NOW())
ON DUPLICATE KEY UPDATE `id` = `id`;