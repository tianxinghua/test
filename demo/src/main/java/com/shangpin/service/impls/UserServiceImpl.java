package com.shangpin.service.impls;

import com.shangpin.mapper.user.UserMapper;
import com.shangpin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.util.List;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/12/11 19:02
 * @Description:
 */
@Service
//@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> findAllUser() {
        return userMapper.findUser();
    }
    @Transactional
    public int registUser(User user){
        user.setSalt((int) (Math.random() * 10));
        int userResult=userMapper.registUser(user);
        System.out.println(user.getUserId());
        int userRoleResult=userMapper.insertUserRole(user.getUserId());
        return userResult;
    }

    @Override
    public User login(HttpServletRequest request, HttpServletResponse response, User user) {
        User userdata = userMapper.login(user);

        if (userdata!=null){
            StringBuffer str=new StringBuffer();
            String data=userdata.getUserName()+"_"+userdata.getChannel();
            try {

                byte[] md5spassword = MessageDigest.getInstance("MD5").digest(data.getBytes());
                for (byte b : md5spassword) {
                    int d = b & 0xff;
                    String hexString = Integer.toHexString(d);
                    if (hexString.length() == 1) {
                        hexString = '0' + hexString;
                    }
                    str.append(hexString);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            Cookie cookie = new Cookie("token", str.toString());
            cookie.setMaxAge(60*60*2);
            cookie.setPath("/");
            //response.setHeader("Access-Control-Allow-Credentials", "true");
            response.addCookie(cookie);

        }
        return userdata;
    }

   /* public static void main(String[] args) {

            try {
                File file = new File("C:\\Users\\Administrator\\Desktop\\file.xls");
                InputStream in = new FileInputStream(file);
                HSSFWorkbook xssfWorkbook = new HSSFWorkbook(in);
                HSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
                int lastRowNum = xssfSheet.getLastRowNum();
                System.out.println(lastRowNum);
            }catch (Exception e){

            }
    }*/
}
