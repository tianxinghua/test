package com.shangpin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/11/12 11:17
 * @Description:
 */
@Controller
@RequestMapping("/")
public class PageController {
    @RequestMapping(value = "page/{param}")
    public String page(@PathVariable(value = "param") String str) {
        System.out.println(str);
        return str;
    }
}
