package com.pinyougou.user.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/showName")
    public Map showName() {
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("用户名是："+loginName);
        Map map = new HashMap();
        map.put("loginName", loginName);
        return map;
    }

}
