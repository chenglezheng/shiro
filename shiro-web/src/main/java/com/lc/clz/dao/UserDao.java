package com.lc.clz.dao;

import com.lc.clz.entity.User;

import java.util.List;

/**
 * Created by chenglezheng on 2018/12/20.
 */
public interface UserDao {

    /**
     * 通过username获得用户对象
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 通过用户名获取角色名称的集合
     * @param username
     * @return
     */
    List<String> getRoleByUserName(String username);

    /**
     * 通过用户名获取权限的集合
     * @param username
     * @return
     */
    List<String> getPermissionByUserName(String username);
}
