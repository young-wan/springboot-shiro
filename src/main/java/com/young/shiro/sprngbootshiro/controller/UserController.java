package com.young.shiro.sprngbootshiro.controller;

import com.young.shiro.sprngbootshiro.entity.User;
import com.young.shiro.sprngbootshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author young
 * @description
 * @date 2021/10/20
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            // 更改user树形
            User principal = (User) subject.getPrincipal();
            PrincipalCollection principalCollection = subject.getPrincipals();
            principal.setDeptName("deptName");
            String realmName = principalCollection.getRealmNames().iterator().next();
            PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(principal, realmName);
            subject.runAs(newPrincipalCollection);
            return "redirect:/index.jsp";
        } catch (AuthenticationException e) {
            System.out.println("系统异常===");
            e.printStackTrace();
        }
        return "redirect:/login.jsp";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }

    @RequestMapping(value = "/register")
    public String register(User user) {
        try {
            userService.register(user);
            return "redirect:/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register.jsp";
        }
    }

    @GetMapping(value = "/getById")
    @ResponseBody
    public void getById(Long id) {
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        System.out.println(principal.toString());
    }
}
