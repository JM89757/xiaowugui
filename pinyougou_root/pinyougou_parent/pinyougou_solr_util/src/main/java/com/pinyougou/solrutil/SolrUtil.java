package com.pinyougou.solrutil;

import com.alibaba.fastjson.JSON;
import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SolrUtil {

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private TbItemMapper itemMapper;

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext*.xml");
//        SolrUtil solrUtil = app.getBean(SolrUtil.class);
        SolrUtil solrUtil = (SolrUtil) app.getBean("solrUtil");
        solrUtil.importItemData();
    }

    public void importItemData() {
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo("1");
        List<TbItem> itemList = itemMapper.selectByExample(example);
        System.out.println("====Goods List====");
        for (TbItem item : itemList) {
            Map specMap = JSON.parseObject(item.getSpec());
            item.setSpecMap(specMap);
            System.out.println(item.getTitle());
        }
        solrTemplate.saveBeans(itemList);
        solrTemplate.commit();
        System.out.println("====End====");
    }


}
