package cn.itcast.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class QueueController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("/send")
    public void send(String text) {
        jmsMessagingTemplate.convertAndSend("itheima", text);
    }

    @RequestMapping("/sendMap")
    public void sendMap() {
        Map map = new HashMap();
        map.put("itheima", 666);
        map.put("itcast", 777);
        jmsMessagingTemplate.convertAndSend("itheima_map", map);
    }

    @RequestMapping("/sendSms")
    public void sendSms() {
        Map map = new HashMap();
        map.put("mobile", "");
        map.put("template_code", "");
        map.put("sign_name", "");
        map.put("param", "{ \"code\":\"\"}");
        jmsMessagingTemplate.convertAndSend("sms", map);
    }
}
