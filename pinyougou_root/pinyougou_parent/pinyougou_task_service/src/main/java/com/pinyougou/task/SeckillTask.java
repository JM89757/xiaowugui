package com.pinyougou.task;

import com.pinyougou.mapper.TbSeckillGoodsMapper;
import com.pinyougou.pojo.TbSeckillGoods;
import com.pinyougou.pojo.TbSeckillGoodsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SeckillTask {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TbSeckillGoodsMapper seckillGoodsMapper;

    @Scheduled(cron = "* * * * * ?")
    public void refreshSeckillGoods() {
        System.out.println("进行了任务的调度" + new Date());

        List goodsIds = new ArrayList(redisTemplate.boundHashOps("seckillGoods").keys());
        System.out.println(goodsIds);

        TbSeckillGoodsExample example = new TbSeckillGoodsExample();
        TbSeckillGoodsExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo("1");
        criteria.andStockCountGreaterThan(0);
        criteria.andStartTimeLessThanOrEqualTo(new Date());
        criteria.andEndTimeGreaterThan(new Date());
        if (goodsIds.size() > 0) {
            criteria.andIdNotIn(goodsIds);
        }

        List<TbSeckillGoods> seckillGoodsList = seckillGoodsMapper.selectByExample(example);
        for (TbSeckillGoods seckillGoods : seckillGoodsList) {
            redisTemplate.boundHashOps("seckillGoods").put(seckillGoods.getId(), seckillGoods);
            System.out.println("更新的秒杀商品：" + seckillGoods.getId());
        }

        System.out.println("将" + seckillGoodsList.size() + "个商品存进redis中");
    }


    @Scheduled(cron = "* * * * * ?")
    public void removeSeckillGoods() {
        System.out.println("移除秒杀商品的任务" + new Date());
        List<TbSeckillGoods> seckillGoodsList = redisTemplate.boundHashOps("seckillGoods").values();
        for (TbSeckillGoods seckillGoods : seckillGoodsList) {
            if (seckillGoods.getEndTime().getTime() < new Date().getTime()) {
                seckillGoodsMapper.updateByPrimaryKey(seckillGoods);

                redisTemplate.boundHashOps("seckillGoods").delete(seckillGoods.getId());
                System.out.println("秒杀商品" + seckillGoods.getId() + "已过期！！！");
            }
        }

        System.out.println("清除秒杀商品的任务");
    }

}
