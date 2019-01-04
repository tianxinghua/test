package com.shangpin.service;

import pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/12/11 19:01
 * @Description:
 */
public interface UserService {
    List<User> findAllUser();
    int registUser(User user);
    User login(HttpServletRequest request, HttpServletResponse response,User user);
}
