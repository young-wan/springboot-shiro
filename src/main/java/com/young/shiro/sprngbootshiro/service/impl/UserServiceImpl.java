package com.young.shiro.sprngbootshiro.service.impl;

import com.young.shiro.sprngbootshiro.dao.UserDao;
import com.young.shiro.sprngbootshiro.entity.User;
import com.young.shiro.sprngbootshiro.service.UserService;
import com.young.shiro.sprngbootshiro.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author young
 * @description
 * @date 2021/10/20
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        // 处理业务调用dao
        // 明文密码进行md5+salt+hash散列
        String salt = SaltUtils.getSalt(8);
        user.setSalt(salt);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User getByName(String username) {
        return userDao.getByName(username);
    }

    @Override
    public User getRolesByUserName(String username) {
        return userDao.getRolesByUserName(username);
    }
}
