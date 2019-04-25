package com.ssm.gbq.shiroRealm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.ssm.gbq.model.Manager;
import com.ssm.gbq.service.ManagerService;

import gbq.ssm.utils.BusinessException;
@Component(value = "myRealm")
public class MyRealm extends AuthorizingRealm{

    @Resource
    private ManagerService managerService;
    
 // 权限检查
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    // 登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        Manager authentication = null;
        try {
            // 通过数据库验证
            authentication = managerService.checkManagerLogin(userName, password);
        } catch (BusinessException exception) {
            throw new AuthenticationException(exception.getErrorMessage());
        }
        if (authentication == null) {
            throw new AuthenticationException("用户名或密码错误");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, password, getName());
        return simpleAuthenticationInfo;
    }
}
