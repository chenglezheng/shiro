package com.lc.clz.controller;

import com.lc.clz.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
            token.setRememberMe(user.getRemeberMe());
            subject.login(token);
        }catch(Exception e){
            return e.getMessage();
        }
        /*if (subject.hasRole("admin")){
            return "有admin权限";
        }*/
        if(subject.isPermitted("admin:update")){
            return "有更新的权利";
        }
        return "权限不足";
    }


    @RequestMapping(value = "/testRole",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String testRole() {
        return "testRole success";
    }

    @RequestMapping(value = "/testRole1",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String testRole1() {
        return "testRole1 success";
    }

    @RequestMapping(value = "/testPer",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String testPer() {
        return "testPer success";
    }

    @RequestMapping(value = "/testPer1",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String testPer1() {
        return "testPer1 success";
    }
}
