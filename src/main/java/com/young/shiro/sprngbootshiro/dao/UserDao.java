package com.young.shiro.sprngbootshiro.dao;

import com.young.shiro.sprngbootshiro.entity.Role;
import com.young.shiro.sprngbootshiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author young
 * @description
 * @date 2021/10/20
 */
@Mapper
@Repository
public interface UserDao{
    void save(User user);

    User getByName(String username);

    User getRolesByUserName(String username);
}
