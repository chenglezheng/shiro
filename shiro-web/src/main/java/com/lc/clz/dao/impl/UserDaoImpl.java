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
        String sql="select USER_NAME,USER_PASSWORD from USERS where USER_NAME=?";
        List<User> list=jdbcTemplate.query(sql, new String[]{username}, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet,int i) throws SQLException{
                User user=new User();
                user.setUsername(resultSet.getString("USER_NAME"));
                user.setPassword(resultSet.getString("USER_PASSWORD"));
                return user;
            }
        });
        //判断集合是否为空
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }


    public List<String> getRoleByUserName(String username) {
        String sql="select role_name from USER_ROLES where USER_NAME=?";
        List<String> list=jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet,int i) throws SQLException{
                return resultSet.getString("role_name");
            }
        });
        //判断集合是否为空
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list;
    }


    public List<String> getPermissionByUserName(String username) {
        String sql="select permission_name from USER_PERMISSION where USER_NAME=?";
        List<String> list=jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet,int i) throws SQLException{
                return resultSet.getString("permission_name");
            }
        });
        //判断集合是否为空
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list;
    }
}
