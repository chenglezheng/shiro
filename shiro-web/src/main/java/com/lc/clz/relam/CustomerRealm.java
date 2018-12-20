package com.lc.clz.relam;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by chenglezheng on 2018/12/20.
 */
public class CustomerRealm extends AuthorizingRealm{

    Map<String,String> userMap=new HashMap<String, String>();

    {
        userMap.put("clz","e76fc5c72441c710bcee02854fc52463");
        super.setName("customerRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName=(String) principalCollection.getPrimaryPrincipal();
        //从数据库或者缓存中获取角色数据
        Set<String> roles=getRoleByUserName(userName);
        //从数据库或者缓存中获取权限数据
        Set<String> permissions=getPermissionByUserName(userName);

        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissions);
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    /**
     * 模拟数据库中获取用户权限
     * @param userName
     * @return
     */
    private Set<String> getPermissionByUserName(String userName){
        Set<String> set=new HashSet<String>();
        set.add("user:delete");
        set.add("user:update");
        return set;
    }

    /**
     * 模拟数据库中获取用户角色
     * @param userName
     * @return
     */
    private Set<String> getRoleByUserName(String userName){
        Set<String> set=new HashSet<String>();
        set.add("admin");
        set.add("user");
        return set;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1.从主体传过来的认证信息中，获得用户名
        String userName=(String) authenticationToken.getPrincipal();

        //2.通过用户名到数据库中获取凭证
        String password=getPasswordByUserName(userName);

        if (password==null){
            return  null;
        }
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo("clz",password,"customerRealm");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("*#~"));
        return authenticationInfo;
    }

    /**
     * 模拟数据库获取密码
     * @param userName
     * @return
     */
    private String getPasswordByUserName(String userName){
        return userMap.get(userName);
    }


    public static void main(String[] args) {
        Md5Hash md5Hash=new Md5Hash("clz123456","*#~");
        System.out.println(md5Hash.toString());
    }
}
