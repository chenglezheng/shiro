package com.lc.clz;

import com.lc.clz.realm.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by chenglezheng on 2018/12/20.
 */
public class CustomerRealmTest {

    CustomerRealm customerRealm=new CustomerRealm();

    @Test
    public void testIsAuthentication() {

        //1.构建SecurityManager对象
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customerRealm);

        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        customerRealm.setCredentialsMatcher(matcher);

        //2.主体提交认证
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("clz", "clz123456");
        subject.login(token);

        System.out.println("isAuthentication:" + subject.isAuthenticated());

        subject.checkRole("admin");

       /* subject.checkPermission("user:insert");*/


       /* subject.logout();

        System.out.println("isAuthentication:"+subject.isAuthenticated());*/
    }
}
