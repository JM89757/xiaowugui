package com.pinyougou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;

@Component
public class ItemSearchListener implements MessageListener {


    @Autowired
    private ItemSearchService itemSearchService;

    @Override
    public void onMessage(Message message) {
        System.out.println(" message start");
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            System.out.println("The listener is :" + text);
            List<TbItem> itemList = JSON.parseArray(text, TbItem.class);
            itemSearchService.importList(itemList);
            System.out.println("import solr repository success");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
