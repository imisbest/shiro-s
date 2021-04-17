package com.csw;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShiroSApplicationTests {

    @Test
    public void shiro() {
        //获取SecurityManager工厂
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //通过工厂获取securityManager对象
        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
        //通过SecurityUtils将securityManager置于shiro环境中
        SecurityUtils.setSecurityManager(securityManager);
        //通过SecurityUtils获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //从前端获取用户名密码封装成Token对象
        AuthenticationToken authenticationToken = new UsernamePasswordToken("Rxx", "111222");
        //主体登录
        subject.login(authenticationToken);
        //是否完成认证程序
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
            // boolean permitted = subject.isPermitted("user:create:*");
            subject.isAuthenticated();
            // List<String> strings = Arrays.asList("user:create", "banner:add:*");
            boolean[] permitted1 = subject.isPermitted("user:create", "admin:create:*");
            // strings.forEach(s -> System.out.println(s));
            for (boolean b : permitted1) {
                System.out.println(b);
            }
        }
        //System.out.println(authenticated);
    }

    @Test
    public void MD5() {
        long time = new Date().getTime();
        Md5Hash md5Hash = new Md5Hash("111222", "ABCD", 1024);
        long time1 = new Date().getTime();
        long ll = time1 - time;
        System.out.println(md5Hash + "][" + ll + "ms");
        //6c92206b09249ec7a5358f74ac6d2d7f
    }
}
