package com.young.shiro.sprngbootshiro.realms;

import com.young.shiro.sprngbootshiro.entity.Role;
import com.young.shiro.sprngbootshiro.entity.User;
import com.young.shiro.sprngbootshiro.service.UserService;
import com.young.shiro.sprngbootshiro.utils.ContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author young
 * @description
 * @date 2021/10/20
 */
public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        UserService userService = (UserService) ContextUtils.getBean("userService");
        User user = userService.getRolesByUserName(primaryPrincipal);
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        List<Role> roles = user.getRoleList();
        if (!CollectionUtils.isEmpty(roles)) {
//            info.addRoles(roles.stream().map(Role::getName).collect(Collectors.toList()));
            roles.forEach(r -> info.addRole(r.getName()));
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        UserService userService = (UserService) ContextUtils.getBean("userService");
        User user = userService.getByName(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        }
        return null;
    }
}