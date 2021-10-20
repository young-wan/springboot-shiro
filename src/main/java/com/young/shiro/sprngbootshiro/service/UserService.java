package com.young.shiro.sprngbootshiro.service;

import com.young.shiro.sprngbootshiro.entity.User;

/**
 * @author young
 * @description
 * @date 2021/10/20
 */
public interface UserService {
    /**
     * 用户注册
     * @param user
     */
    void register(User user);

    User getByName(String username);


    User getRolesByUserName(String username);
}
