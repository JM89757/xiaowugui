package cn.itcast.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebDemo {

    @Autowired
    private Environment environment;

    @RequestMapping("/info")
    public String info() {
        return "Hello Spring Boot " + environment.getProperty("url");
    }
}
