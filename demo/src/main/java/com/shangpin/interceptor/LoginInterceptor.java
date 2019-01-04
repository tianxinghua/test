package com.shangpin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/12/24 10:20
 * @Description:
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String XRequested =request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(XRequested)){
            return true;
        }
        System.out.println("进入拦截器");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equalsIgnoreCase("token")){
                return true;
            }
        }
        response.sendRedirect(request.getContextPath()+"/page/login");
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
