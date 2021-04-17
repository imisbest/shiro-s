package com.csw.realm;

import com.csw.entity.User2;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        //User2 user2 = new User2("1", "Rxx", "6c92206b09249ec7a5358f74ac6d2d7f");
        List<String> roles = Arrays.asList("admin", "user", "vip");
        List<String> resources = Arrays.asList("admin:create:*", "user:*");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(resources);
        return authorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //通过authenticationToken获取主体信息
        //String principal = (String) authenticationToken.getPrincipal();
        //通过主体信息去数据库查询账号密码
        User2 user2 = new User2("1", "Rxx", "6c92206b09249ec7a5358f74ac6d2d7f");
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user2.getUsername(), user2.getPassword(), ByteSource.Util.bytes("ABCD"), this.getName());
        return authenticationInfo;
    }
}
