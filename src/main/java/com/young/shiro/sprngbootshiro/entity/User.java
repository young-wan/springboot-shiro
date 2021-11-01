package com.young.shiro.sprngbootshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author young
 * @description
 * @date 2021/10/20
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;

    private String username;

    private String password;

    private String salt;

    List<Role> roleList;

    private String deptName;
}
