package com.lc.clz.dao.impl;

import com.lc.clz.dao.UserDao;
import com.lc.clz.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenglezheng on 2018/12/20.
 */
@Component
public class UserDaoImpl implements UserDao{

   @Resource
   private JdbcTemplate jdbcTemplate;

    public User getUserByUserName(String username) {
        String sql="select user_name,USER_PWD from user where user_name=?";
        List<User> list=jdbcTemplate.query(sql, new String[]{username}, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet,int i) throws SQLException{
                User user=new User();
                user.setUsername(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("user_pwd"));
                return user;
            }
        });
        //判断集合是否为空
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }
}
