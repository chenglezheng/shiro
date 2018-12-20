package com.lc.clz.dao;

import com.lc.clz.entity.User;

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
}
