package com.shangpin.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import pojo.User;

import java.util.List;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/12/11 17:39
 * @Description:
 */
@Repository
@Mapper
public interface UserMapper {
    List<User> findUser();
    int registUser(User user);
    int insertUserRole(int userId);
    User login(User user);
}
