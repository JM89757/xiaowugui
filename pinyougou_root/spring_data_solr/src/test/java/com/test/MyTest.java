package com.test;

import cn.itcast.pojo.TbItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-solr.xml")
public class MyTest {

    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void add() {
        TbItem tbItem = new TbItem();
        tbItem.setId(1L);
        tbItem.setBrand("RedMi");
        tbItem.setCategory("手机");
        tbItem.setGoodsId(1L);
        tbItem.setSeller("小米官方旗舰店");
        tbItem.setTitle("RedMi K20");
        tbItem.setPrice(new BigDecimal(1999));
        solrTemplate.saveBean(tbItem);
        solrTemplate.commit();
    }

    @Test
    public void findById() {
        TbItem item = solrTemplate.getById(1, TbItem.class);
        System.out.println(item.getTitle());
    }

    @Test
    public void deleById() {
        solrTemplate.deleteById("1");
        solrTemplate.commit();
    }

    @Test
    public void addList() {
        List list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            TbItem tbItem = new TbItem();
            tbItem.setId(i + 1L);
            tbItem.setBrand("RedMi");
            tbItem.setCategory("手机");
            tbItem.setGoodsId(1L);
            tbItem.setSeller("小米官方旗舰店"+i);
            tbItem.setTitle("RedMi K20-" + i);
            tbItem.setPrice(new BigDecimal(1999));
            list.add(tbItem);
        }
        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }


    @Test
    public void pageQuery() {
        Query query = new SimpleQuery("*:*");
        query.setOffset(0);
        query.setRows(20);
        Page<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        List<TbItem> list = page.getContent();
        showList(list);
    }

    private void showList(List<TbItem> list) {
        for (TbItem tbItem : list) {
            System.out.println(tbItem.getTitle() + "价格是：" + tbItem.getPrice());
        }
    }

    @Test
    public void pageQueryMutil() {
        Query query = new SimpleQuery("*:*");
        Criteria criteria = new Criteria("item_title").contains("1");
        criteria = criteria.and("item_title").contains("5");
        query.addCriteria(criteria);
        query.setOffset(0);
        query.setRows(20);
        Page<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        List<TbItem> list = page.getContent();
        showList(list);
    }

    @Test
    public void test1() {
        Query query = new SimpleQuery("*:*");
        Criteria criteria = new Criteria("item_title").contains("1");
        criteria = criteria.and("item_seller").is("小米");
        query.addCriteria(criteria);
        query.setOffset(0);
        query.setRows(20);
        Page<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("总页数：" + page.getTotalPages());
        List<TbItem> list = page.getContent();
        showList(list);
    }

    @Test
    public void delete() {
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }


}
