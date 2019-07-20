package cn.itcast.demo.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Consumer {
    @JmsListener(destination = "itheima")
    public void read(String text) {
        System.out.println("Message is ;" + text);
    }

    @JmsListener(destination = "itheima_map")
    public void readMap(Map map) {
        System.out.println(map);
    }


}
