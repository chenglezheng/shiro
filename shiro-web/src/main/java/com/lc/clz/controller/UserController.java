package com.lc.clz.controller;

import com.lc.clz.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenglezheng on 2018/12/20.
 */

@Controller
public class UserController {

    @RequestMapping(value = "/subLogin",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String subLogin(User user){

        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try{
            subject.login(token);
        }catch(Exception e){
            return e.getMessage();
        }
        return "登陆成功";
    }

}
