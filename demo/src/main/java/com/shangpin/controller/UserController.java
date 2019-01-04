package com.shangpin.controller;

import com.shangpin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.ResultResp;
import pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/11/26 15:10
 * @Description:
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("index")
    public String loginOrRegist(User user, Integer type) {
        System.out.println(user);
        return "index";
    }

    @RequestMapping("register")
    @ResponseBody
    public ResultResp register(@Valid User user, BindingResult bindingResult) {

        System.out.println(user);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return new ResultResp().setEerrrResp(bindingResult.getFieldError().getDefaultMessage());
        }

        Integer result=userService.registUser(user);
        return new ResultResp().setSuccessResp();
    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public ResultResp login(HttpServletRequest request, HttpServletResponse response, User user){
        System.out.println(1111);
        User userob = userService.login(request,response,user);
        if (userob!=null){
            return new ResultResp().setSuccessResp(userob);
        }
        return new ResultResp().setEerrrResp("用户名或密码错误！");
    }
}
